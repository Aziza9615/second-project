package com.example.my_second.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_second.data.model.Task
import com.example.my_second.data.task.TaskDao

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun TaskDao(): TaskDao
}