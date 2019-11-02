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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth.addAuthStateListener {
            if (it.currentUser != null) {
                button.text = "Logout"
            } else {
                button.text = "Sign In"
            }
        }

        button.setOnClickListener {
            if (button.text == "Sign In") {
                if (!nameInpLayout.editText?.text.isNullOrEmpty() &&
                    !passInpLayout.editText?.text.isNullOrEmpty()
                ) {
                    if (passInpLayout.editText?.text?.length!! > 6) {
                        signIn(
                            nameInpLayout.editText?.text.toString(),
                            passInpLayout.editText?.text.toString()
                        )
                    } else {
                        passInpLayout.apply {
                            isErrorEnabled = true
                            error = "Password length should be greater than 6"
                        }

                    }
                } else {
                    passInpLayout.apply {
                        isErrorEnabled = true
                        error = "Cannot ne Empty"
                    }
                    nameInpLayout.apply {
                        isErrorEnabled = true
                        error = "Cannot ne Empty"
                    }
                }
            } else {
                auth.signOut()
            }
        }
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
}
