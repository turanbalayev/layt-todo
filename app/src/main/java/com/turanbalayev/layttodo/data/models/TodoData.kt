package com.turanbalayev.layttodo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turanbalayev.layttodo.data.models.Priority


@Entity(tableName = "todo_table")
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)