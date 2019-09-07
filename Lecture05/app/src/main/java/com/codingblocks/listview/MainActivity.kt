package com.codingblocks.listview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val movies = arrayOf("Iron Man", "Thor", "Captain America", "Black Panther", "Endgame")
    val yearofRelease = arrayOf("2008", "2010", "2011", "2018", "2019")
    val actors = arrayOf("RDJ", "Chris Hemsworth", "Chris Evans", "Chadwick", "Tom Holland")
    val image = arrayOf(
        R.drawable.ironman,
        R.drawable.thor,
        R.drawable.cap,
        R.drawable.blackpanther,
        R.drawable.avenger
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
//        lv = findViewById(R.id.lv)


        list.addAll(
            listOf(
                Movie("Iron Man", "", "", 1)
            )
        )
        val movie = Movie("Iron Man", "", "", 1)
        Log.i("Class", "" + movie.toString())

//        lv.adapter =
//            ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_expandable_list_item_1,
//                list
//            )


    }
}

data class Movie(
    val name: String,
    val year: String,
    val actor: String,
    val image: Int
)
