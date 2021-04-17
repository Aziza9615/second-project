package com.example.my_second.data.api

import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskApi {
    @Headers(
        "Authorization:  Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
        "client_id: fa2ce054399444 7f9c0e610a6d5c90ee",
        "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @GET("tasks")
    fun fetchTasks(@Query("project_id")id: Long?): Call<MutableList<Task>>

    @Headers(
            "Authorization: Bearer 18d41187422aa8a8949e8a12f437b961c34b0dce",
            "client_id: e71d58b4ed1b48abb2b8530df55be03f",
            "client_secret: 324fa28e17164dc8b799b373f3480806"
    )

    @POST("tasks/{f}/close")
    fun changeStateOfTask(@Path("id")id: Long?) : Call<Unit>

    @DELETE("task/{id}")
    fun deleteTask(@Path("id")id: Long?) : Call<Unit>
}


