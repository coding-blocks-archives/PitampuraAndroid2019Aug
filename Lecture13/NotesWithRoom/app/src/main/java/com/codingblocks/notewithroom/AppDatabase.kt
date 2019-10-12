package com.codingblocks.notewithroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingblocks.notewithroom.dao.TodoDao
import com.codingblocks.notewithroom.models.Todo

@Database(entities = [Todo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}