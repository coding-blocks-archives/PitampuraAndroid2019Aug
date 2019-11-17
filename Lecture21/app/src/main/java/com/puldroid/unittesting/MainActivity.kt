package com.puldroid.unittesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        fun calcFare(dist: Int, time: Int): Int {
            var fare = 50
            if (dist > 2) fare += (dist - 2) * 10
            if (time > 10) fare += ((time - 10) / 5) * 10
            return fare
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
