package com.example.my_second.data.local

import com.example.my_second.data.project.ProjectApi
import com.example.my_second.data.task.TaskApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.todoist.com/rest/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val projectApi = retrofit.create(ProjectApi::class.java)
    val tasksApi = retrofit.create(TaskApi::class.java)
}


