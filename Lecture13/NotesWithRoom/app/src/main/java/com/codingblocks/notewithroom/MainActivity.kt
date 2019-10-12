package com.codingblocks.notewithroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.codingblocks.notewithroom.models.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "todo.db"
        )
            .allowMainThreadQueries()
            .build()
    }


    val todoList = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList.addAll(db.todoDao().getAllTodo())

        todoRv.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(todoList)
        todoRv.adapter = adapter

        button.setOnClickListener {
            db.todoDao().insertTodo((Todo(name = editText.text.toString(), status = false)))
            todoList.clear()
            todoList.addAll(db.todoDao().getAllTodo())
            adapter.notifyDataSetChanged()

        }
    }
}
