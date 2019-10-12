package com.codingblocks.notewithroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codingblocks.notewithroom.models.Todo

@Dao
interface TodoDao {

    @Insert
    fun insertTodo(todo:Todo)

    @Insert
    fun insertAll(todoList:ArrayList<Todo>)

    @Query("Select * FROM TODO")
    fun getAllTodo() : List<Todo>

    @Delete
    fun delete(todo:Todo)
}