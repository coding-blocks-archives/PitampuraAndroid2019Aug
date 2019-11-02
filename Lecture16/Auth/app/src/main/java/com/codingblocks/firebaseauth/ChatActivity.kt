package com.codingblocks.firebaseauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}

data class ChatMessage(
    val message: String,
    val name: String,
    val time: Long
)
