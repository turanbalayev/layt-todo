package com.turanbalayev.layttodo.data.repository

import androidx.lifecycle.LiveData
import com.turanbalayev.layttodo.data.TodoDao
import com.turanbalayev.layttodo.data.models.TodoData

class TodoRepository(private val todoDao: TodoDao) {
    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertData(todoData)
    }
}