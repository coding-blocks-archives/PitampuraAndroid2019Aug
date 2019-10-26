package com.codingblocks.firebaseauth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

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
                if (it.localizedMessage.contains("exist")) {
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
