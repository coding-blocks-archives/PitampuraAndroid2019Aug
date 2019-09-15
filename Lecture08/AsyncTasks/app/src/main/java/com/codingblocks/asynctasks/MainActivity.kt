package com.codingblocks.asynctasks

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
//            val currentTime = System.currentTimeMillis()

//            while (System.currentTimeMillis() < currentTime + 10000){}
            val handler = Handler()
            handler.postDelayed(object :Runnable{
                override fun run() {
                    root.setBackgroundColor(Color.RED)
                }
            },10000)
        }

    }
}
