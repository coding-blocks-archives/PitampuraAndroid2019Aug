package com.codingblocks.notewithroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocks.notewithroom.models.Todo

class TodoAdapter(var tasks: ArrayList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(tasks[position])
    }


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) {
            itemView.findViewById<TextView>(android.R.id.text1).text = todo.name
        }
    }

}
