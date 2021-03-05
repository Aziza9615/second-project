package com.example.my_second.data.local

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object{
        private lateinit var db: AppDatabase
        fun getDatabase(): AppDatabase =
            db
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
                .databaseBuilder(applicationContext, AppDatabase::class.java, "Task_db")
                .allowMainThreadQueries()
                .build()
    }
}