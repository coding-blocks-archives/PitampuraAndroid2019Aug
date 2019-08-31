package com.codingblocks.kotlinbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var array: Array<String>? = null
    lateinit var names: Array<String>
    var name = "Pulkit"
    val buttonArray:Array<Button> by lazy {
        arrayOf(button,button2,button3)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a = savedInstanceState?.get("hello")
        array?.get(0)
        array?.set(0, "Hello")


        names = Array(3, { "Pulkit" })


        names[1]


        //Set
        names[0] = "Pulkit"

        array = arrayOf("Hello", "World", "Kotlin", "Basics")


        //
        button.setOnClickListener {

        }
        val buttonArray:Array<Button> = arrayOf(button,button2,button3)

        buttonArray.forEach {
            it.setOnClickListener(this)
        }
    }

    public fun helloWorld() : String = "hello"
}
