package com.example.my_second.data.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.my_second.data.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertTask(data: List<Task>?)

    @Query("SELECT * FROM task")
    fun fetchTasks(): List<Task>
}
