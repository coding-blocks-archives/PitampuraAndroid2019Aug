package com.codingblocks.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credential.EXTRA_KEY
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

const val TAG = "PHONEAUTH"
const val PHONE_NO = "phoneNo"
val RESOLVE_HINT = 1001

class MainActivity : AppCompatActivity() {


    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    var id:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Log.d(TAG, "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {

                Log.w(TAG, "onVerificationFailed", e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")
                id = verificationId
            }
        }

        requestHint()
        auth.addAuthStateListener {
            if (it.currentUser != null) {
                button.text = "Logout"
            } else {
                button.text = "Sign In"
            }
        }
        button.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                nameInpLayout.editText?.text.toString(), // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks
        }

        verify.setOnClickListener {
            val creds = PhoneAuthProvider.getCredential(
                id,passInpLayout.editText?.text.toString()
            )
            signInWithPhoneAuthCredential( creds)
        }

//        button.setOnClickListener {
//            if (button.text == "Sign In") {
//                if (!nameInpLayout.editText?.text.isNullOrEmpty() &&
//                    !passInpLayout.editText?.text.isNullOrEmpty()
//                ) {
//                    if (passInpLayout.editText?.text?.length!! > 6) {
//                        signIn(
//                            nameInpLayout.editText?.text.toString(),
//                            passInpLayout.editText?.text.toString()
//                        )
//                    } else {
//                        passInpLayout.apply {
//                            isErrorEnabled = true
//                            error = "Password length should be greater than 6"
//                        }
//
//                    }
//                } else {
//                    passInpLayout.apply {
//                        isErrorEnabled = true
//                        error = "Cannot ne Empty"
//                    }
//                    nameInpLayout.apply {
//                        isErrorEnabled = true
//                        error = "Cannot ne Empty"
//                    }
//                }
//            } else {
//                auth.signOut()
//            }
//        }
    }

    private fun requestHint() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()

        val apiClient = GoogleApiClient.Builder(this)
            .addApi(Auth.CREDENTIALS_API)
            .enableAutoManage(this) {
                Log.i(TAG, "Mobile Number: ${it.errorMessage}")
            }.build()

        val intent = Auth.CredentialsApi.getHintPickerIntent(apiClient, hintRequest)
        startIntentSenderForResult(
            intent.intentSender,
            RESOLVE_HINT, null, 0, 0, 0
        )
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
    }

    private fun signIn(name: String, password: String) {
        auth.createUserWithEmailAndPassword(name, password)
            .addOnCompleteListener {

            }.addOnSuccessListener {

            }.addOnFailureListener {
                if (it.localizedMessage.contains("already")) {
                    logIn(name, password)
                }
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }

    private fun logIn(name: String, password: String) {
        auth.signInWithEmailAndPassword(name, password).addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                val cred = data?.getParcelableExtra(EXTRA_KEY) as Credential
                if (cred != null) {
                    // cred.od; <-- E.164 format phone number on 10.2.+ devices
                    val unformattedPhone = cred.id
                    Log.e(TAG, "Client connection failed: $unformattedPhone")
                    nameInpLayout.editText?.setText(unformattedPhone.substring(startIndex = 3))

                }
            }
        }
    }
}
