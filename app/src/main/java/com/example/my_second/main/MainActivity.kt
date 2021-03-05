package com.example.my_second.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.my_second.R
import com.example.my_second.newRepository.TaskRepository

class MainActivity : AppCompatActivity() {

    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}