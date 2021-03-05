package com.example.my_second.data.network

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

    val TaskApi = retrofit.create(com.example.my_second.data.network.TaskApi::class.java)
    val ProjectApi = retrofit.create(com.example.my_second.data.network.TaskApi::class.java)
}
