package com.example.my_second.data.network

import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.http.GET

interface ProjectApi {
    @GET("project")
    fun fetchProject(): Call<MutableList<Project>>
}