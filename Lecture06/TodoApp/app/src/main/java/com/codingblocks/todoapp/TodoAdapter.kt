package com.codingblocks.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(val todos: ArrayList<String>) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflator = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflator.inflate(R.layout.item_todo, parent, false))

    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(todos[position])
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(todo: String) {
        itemView.textView.text = todo
    }
}
