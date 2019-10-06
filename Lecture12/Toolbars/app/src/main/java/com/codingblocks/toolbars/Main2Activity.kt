package com.codingblocks.toolbars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar_2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
