package com.codingblocks.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val NAME = "name"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            val a = editText.text.toString()

            val i = Intent(this, Main2Activity::class.java)
            i.putExtra(NAME, a)
            startActivity(i)
        }
    }

    fun implicit_intent(view: View) {
        val a = editText.text.toString()

    }
}

