package com.example.my_second.data.api

import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskApi {
    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @GET("tasks")
    fun fetchTasks(@Query("project_id")id: Long?): Call<MutableList<Task>>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @POST("tasks/{f}/close")
    fun changeStateOfTask(@Path("id")id: Long?) : Call<Unit>

    @DELETE("task/{id}")
    fun deleteTask(@Path("id")id: Long?) : Call<Unit>
}

