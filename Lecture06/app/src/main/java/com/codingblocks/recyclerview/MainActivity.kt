package com.codingblocks.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = arrayListOf<String>("Iron Man", "Thor", "Captain America", "Black Panther", "Endgame")

        rv.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rv.adapter = MovieAdapter(movies)
    }
}
