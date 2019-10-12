package com.codingblocks.notewithroom

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
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
    val toast = MutableLiveData<Boolean>()


    val todoList = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = TodoAdapter(todoList)

        toast.observe(this, Observer {
            button.isEnabled = it

            if (it) {
                Toast.makeText(this, "Show Toast", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Hide Toast", Toast.LENGTH_SHORT).show()
            }
        })

        db.todoDao().getAllTodo().observe(this, Observer {
            todoList.clear()
            todoList.addAll(it)
            adapter.notifyDataSetChanged()
            Handler().postDelayed({
                toast.value = true
            },3000)
        })
        todoRv.layoutManager = LinearLayoutManager(this)
        todoRv.adapter = adapter

        button.setOnClickListener {
            toast.value = false


            db.todoDao().insertTodo((Todo(name = editText.text.toString(), status = false)))
        }
    }
}
