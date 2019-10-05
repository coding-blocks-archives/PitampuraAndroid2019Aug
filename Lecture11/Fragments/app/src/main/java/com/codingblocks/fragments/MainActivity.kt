package com.codingblocks.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()

        button.setOnClickListener {
            bundle.putString("NAME","DC")
            val frag = BlankFragment()
            frag.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,frag
                ).commit()
        }

        button2.setOnClickListener {
            bundle.putString("NAME","MARVEL")
            val frag = BlankFragment()
            frag.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,frag
                ).commit()
        }


    }
}
