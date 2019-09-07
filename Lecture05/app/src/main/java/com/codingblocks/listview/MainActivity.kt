package com.codingblocks.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
//        lv = findViewById(R.id.lv)


        list.addAll(
            listOf(
                "Iron Man",
                "Captain America",
                "Thor",
                "Captain Marvel",
                "Endgame",
                "Hulk",
                "Iron Man",
                "Captain America",
                "Thor",
                "Captain Marvel",
                "Endgame",
                "Hulk",
                "Iron Man",
                "Captain America",
                "Thor",
                "Captain Marvel",
                "Endgame",
                "Hulk"
            )
        )
        lv.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                list
            )


    }
}
