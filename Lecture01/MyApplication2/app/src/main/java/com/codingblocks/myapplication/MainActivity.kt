package com.codingblocks.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val student = Home("Pulkit", 1)
        print(student)

        Log.i("OnCreate", "On Create Called")

    }

    override fun onStart() {
        super.onStart()
        Log.i("OnCreate", "On onStart Called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("OnCreate", "On onPause Called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("OnCreate", "On onResume Called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("OnCreate", "On onStop Called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("OnCreate", "On onDestroy Called")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("OnCreate", "On onRestart Called")

    }
}
