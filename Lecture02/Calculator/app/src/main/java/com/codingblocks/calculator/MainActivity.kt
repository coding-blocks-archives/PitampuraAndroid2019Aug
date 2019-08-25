package com.codingblocks.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        var value1 = Integer.parseInt(number1.text.toString())

        var value2 = number2.text.toString().toInt()


        when (view.id) {
            R.id.btn1 -> {

            }
            R.id.plusBtn -> {

                resultTv.text = "${value1 + value2}"

            }
            R.id.subtractBtn -> {
                resultTv.text = "${value1 - value2}"

            }
            R.id.divideBtn -> {
                resultTv.text = "${value1 / value2}"

            }
            R.id.multiplyBtn -> {
                resultTv.text = "${value1 * value2}"

            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn1.setOnClickListener {
//            Log.i("TEXT", "Display Text ${et1.text}")
//
//        }
        btn1.setOnClickListener(this)
        plusBtn.setOnClickListener(this)
        subtractBtn.setOnClickListener(this)
        divideBtn.setOnClickListener(this)
        multiplyBtn.setOnClickListener(this)

    }
}
