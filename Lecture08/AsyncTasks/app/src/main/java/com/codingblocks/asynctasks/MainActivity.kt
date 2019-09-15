package com.codingblocks.asynctasks

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val cTask = CountTask()
            cTask.execute(10)
        }


    }

    inner class CountTask : AsyncTask<Int, Int, Void>() {
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            text.setText(values[0].toString())
        }

        override fun doInBackground(vararg number: Int?): Void? {
            Log.i("AsynckTask", "Work Started")
            val number = number[0]
            for (i in 0..number!!) {
                waitNsec(1)
                publishProgress(i)
            }
            Log.i("AsynckTask", "Work Ended")


            return null
        }

        private fun waitNsec(n: Int) {
            val currentTime = System.currentTimeMillis()
            while (System.currentTimeMillis() < currentTime + n * 1000) {
            }
        }

    }
}
