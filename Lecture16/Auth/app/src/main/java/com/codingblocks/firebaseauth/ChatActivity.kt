package com.codingblocks.firebaseauth

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import java.text.DateFormat
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


        val query = FirebaseDatabase.getInstance()
            .reference
            .child("messages")

        val options = FirebaseListOptions.Builder<ChatMessage>()
            .setLayout(R.layout.item_chat)
            .setQuery(query, ChatMessage::class.java)
            .build()

        val adapter = object : FirebaseListAdapter<ChatMessage>(options) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                val message = v.findViewById<TextView>(R.id.message)
                val name = v.findViewById<TextView>(R.id.name)
                val time = v.findViewById<TextView>(R.id.time)

                message.text = model.message
                name.text = model.name
                time.text = android.text.format.DateFormat.format("dd-MMM-yy HH:mm:ss",model.time)
            }
        }
        adapter.startListening()

        messages.adapter = adapter
    }
}

data class ChatMessage(
    val message: String,
    val name: String,
    val time: Long
)
{
    constructor() : this("","",0L)
}

/**
 *{
"rules": {
"messages" : {
".write":"auth != null",
".read":"auth != null",
"$msg" : {
".validate": "newData.hasChildren(['name','time','message'])",
"message":{
".validate":"newData.isString()"
}
}
},
"message" : {
".write":true,
".read":true
}
}
}
 **/
