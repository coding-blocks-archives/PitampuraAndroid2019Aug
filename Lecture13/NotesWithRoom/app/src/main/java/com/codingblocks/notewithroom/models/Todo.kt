package com.codingblocks.notewithroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val status: Boolean,
    val date: String = "!2-Oct-2019"
)