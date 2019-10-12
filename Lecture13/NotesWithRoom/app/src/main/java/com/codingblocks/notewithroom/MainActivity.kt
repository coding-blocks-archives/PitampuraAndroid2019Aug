package com.codingblocks.notewithroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    val todoList = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = TodoAdapter(todoList)

        db.todoDao().getAllTodo().observe(this, Observer {
            todoList.clear()
            todoList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        todoRv.layoutManager = LinearLayoutManager(this)
        todoRv.adapter = adapter

        button.setOnClickListener {
            db.todoDao().insertTodo((Todo(name = editText.text.toString(), status = false)))
        }
    }
}
