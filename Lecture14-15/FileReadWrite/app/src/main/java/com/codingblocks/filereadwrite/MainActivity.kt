package com.codingblocks.filereadwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("FILES","Files dir ${getExternalFilesDir(Environment.getDataDirectory().absolutePath)}")
        /**
        No need to ask permissions
         **/
//        val file = File(getExternalFilesDir(Environment.getDataDirectory().absolutePath),"myfile.txt")

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"myfile.txt")

        file.writeText("Hello World")

        button.setOnClickListener {
            file.appendText(editText.text.toString())
        }
        button2.setOnClickListener {
            editText.setText(file.readText())
        }
    }
}
