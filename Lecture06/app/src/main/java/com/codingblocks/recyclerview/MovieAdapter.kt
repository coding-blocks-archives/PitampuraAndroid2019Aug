package com.codingblocks.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val movies: ArrayList<String>) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflator = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflator.inflate(R.layout.item_movie, parent, false))

    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: String) {
        itemView.titleTv.text = movie
    }
}

