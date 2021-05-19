package com.example.my_second.data.note

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.my_second.data.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertTask(data: List<Task>?)

//    @Query("SELECT * FROM task")
//    fun fetchTasks(): List<Task>
}
