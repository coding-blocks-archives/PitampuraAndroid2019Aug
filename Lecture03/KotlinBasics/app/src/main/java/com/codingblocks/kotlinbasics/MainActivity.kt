package com.codingblocks.kotlinbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var array: Array<String>? = null
    lateinit var names: Array<String>
    var name = "Pulkit"
    val buttonArray: Array<Button> by lazy {
        arrayOf(button, button2, button3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ll = LinearLayout(this)
        setContentView(ll)
        ll.orientation = LinearLayout.VERTICAL
//        ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        for (k in 0..2) {
            val innerll = LinearLayout(this)
            innerll.orientation = LinearLayout.HORIZONTAL
        ll.addView(innerll)
            for (i in 0..2) {
                val button = Button(this)
                button.text = "Button ${i + 1}"
                button.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                innerll.addView(button)
            }
        }
//        setContentView(R.layout.activity_main)

//        createLayout()

//        kotlinBasics()

    }

    private fun kotlinBasics() {
        array?.get(0)
        array?.set(0, "Hello")
        names = Array(3) { "Pulkit" }
        //Get
        val nameValeue = names[1]
        //Set
        names[0] = "Pulkit"
        array = arrayOf("Hello", "World", "Kotlin", "Basics")
        //
        button.setOnClickListener {

        }
        val buttonArray: Array<Button> = arrayOf(button, button2, button3)

        buttonArray.forEach {
            it.setOnClickListener(this)
        }
    }

    private fun createLayout() {
        val button = Button(this)
        frame.addView(button)
        button.apply {
            text = "Click Me"
            gravity = Gravity.CENTER
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Hello Toast",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }


    public fun helloWorld(): String = "hello"
}

