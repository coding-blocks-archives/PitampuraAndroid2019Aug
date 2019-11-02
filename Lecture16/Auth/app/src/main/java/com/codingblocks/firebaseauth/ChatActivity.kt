package com.codingblocks.firebaseauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val user = FirebaseAuth.getInstance().currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
            //Write Your Own Name Here
            .setDisplayName("Pulkit Aggarwal")
            .build()

        user?.updateProfile(profileUpdates)

        button2.setOnClickListener {

            FirebaseDatabase.getInstance().reference.child("messages")
                .push()
                .setValue(
                    ChatMessage(
                        input.text.toString(),
                        FirebaseAuth.getInstance().currentUser?.displayName!!,
                        Date().time
                    )
                )
            input.setText("")
        }
    }
}

data class ChatMessage(
    val message: String,
    val name: String,
    val time: Long
)
