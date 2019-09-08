package com.codingblocks.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.addAll(arrayListOf<String>("Iron Man", "Thor", "Captain America", "Black Panther", "Endgame"))

//        rv.layoutManager = GridLayoutManager(this,3, RecyclerView.HORIZONTAL,false)
        rv.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val adapter = TodoAdapter(list)
        rv.adapter = adapter

        button.setOnClickListener {
            if (inputLayout.editText?.text.isNullOrEmpty()) {
                inputLayout.isErrorEnabled = true
                inputLayout.error = "Text Cannot Be Empty"
            } else {
                inputLayout.isErrorEnabled = false
                list.add(inputLayout.editText?.text.toString())
                adapter.notifyDataSetChanged()

            }
        }
    }
}
