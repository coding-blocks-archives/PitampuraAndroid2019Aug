package com.codingblocks.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val NAME = "name"

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.button1 -> {
                val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${editText.text}"))
                i.putExtra(Intent.EXTRA_TEXT, "Hello")
                i.putExtra(Intent.EXTRA_SUBJECT, "Sample Subject")
                startActivity(i)
            }
            R.id.button2 -> {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://${editText.text}"))
                startActivity(Intent.createChooser(i, "Select Browser"))


            }
            R.id.button3 -> {
                val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${editText.text}"))
                startActivity(i)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)

    }

    fun implicit_intent(view: View) {
        val a = editText.text.toString()

        val i = Intent(this, Main2Activity::class.java)
        i.putExtra(NAME, a)
        startActivity(i)
    }
}

