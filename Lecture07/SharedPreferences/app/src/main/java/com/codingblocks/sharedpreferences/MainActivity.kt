package com.codingblocks.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences =
            getSharedPreferences("login", Context.MODE_PRIVATE)

        editText.setText(sharedPreferences.getString("username", "Default"))

        editText2.setText(sharedPreferences.getString("password", "Default"))

        save.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        save.setOnClickListener {
            var username: String = ""
            var password: String = ""
            if (editText.text.toString().isNotEmpty()) {
                username = editText.text.toString()
            }
            if (editText2.text.toString().isNotEmpty()
                && editText2.text.toString().vowelLength() == 0
            ) {
                password = editText2.text.toString()
            }
            sharedPreferences.edit { putString("username", username) }
            sharedPreferences.edit { putString("password", password) }

            isName().otherwise {
                Toast.makeText(this, "Diaog Button Clicked", Toast.LENGTH_SHORT).show()
            }
        }


        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alert Dialog")
            .setMessage("This is a simple Dialog")
            .setPositiveButton("ok") { dailog, int ->
                Toast.makeText(this, "Diaog Button Clicked", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("Cancel") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setCancelable(false)
            .show()

    }

    inline fun Boolean.otherwise(block: () -> Unit) = if (this) null else block()


    fun isName(name: String): Boolean {
        if (name.isEmpty())
            return false
        else
            return true
    }

    /**
     *<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
    <map>
    <boolean name="welcomeFlowFinished" value="true" />
    <float name="welcomeFlowTime" value="7.35" />
    <set name="devices">
    <string>Nexus 5</string>
    <string>OnePlus 3T</string>
    <string>Pixel 2XL</string>
    </set>
    <long name="lastVisit" value="1541627846640" />
    <int name="welcomeFlowSkippedScreens" value="0" />
    <string name="userName">Alex</string>
    </map>
     **/

    fun String.vowelLength(): Int {
        var count = 0
        this.forEach {
            if (it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u') {
                count++
            }
        }
        return count
    }
}

