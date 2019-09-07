package com.codingblocks.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

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
    val list: ArrayList<Movie> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..99) {
            val random = Random().nextInt(5)
            list.add(
                Movie(
                    movies[random],
                    yearofRelease[random],
                    actors[random],
                    image[random]
                )
            )
        }

//        lv.adapter =
//            ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_expandable_list_item_1,
//                list
//            )
        lv.adapter = MovieAdapter(list)

    }
}

data class Movie(
    val name: String,
    val year: String,
    val actor: String,
    val image: Int
)

class MovieAdapter(val movies: ArrayList<Movie>) : BaseAdapter() {
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_movie, parent, false)
        view.titleTv.text = movies[pos].name + "(" + movies[pos].year + ")"
        view.actorTv.text = movies[pos].actor
        view.imageView.setImageResource(movies[pos].image)
        return view
    }

    override fun getItem(pos: Int): Movie = movies[pos]

    override fun getItemId(pos: Int): Long = pos.toLong()


    override fun getCount(): Int = movies.size
}
