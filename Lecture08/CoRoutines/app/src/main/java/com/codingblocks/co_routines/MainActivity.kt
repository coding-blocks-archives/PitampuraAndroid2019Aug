package com.codingblocks.co_routines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {

            launch {
                for (i in 0..10) {
                    delay(1000)
                    text.setText(i.toString())
                }
            }
//            async {
//                for (i in 0..10) {
//                    delay(1000)
//                    text.setText(i.toString())
//                }
//            }
        }


    }
}
