package com.example.my_second.data.network

import com.example.my_second.data.model.Task
import retrofit2.http.GET

interface TaskApi {
    @GET("/tasks")
    fun getAllTasks(): MutableList<Task>
}