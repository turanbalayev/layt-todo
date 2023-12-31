package com.turanbalayev.layttodo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.turanbalayev.layttodo.data.models.TodoData

@Dao
interface TodoDao {

    @Query("select * from todo_table order by id asc")
    fun getAllData(): LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoData: TodoData)
}