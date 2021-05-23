package com.example.my_second.data.api

import com.example.my_second.data.model.Task
import com.google.android.gms.tasks.Tasks
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface TaskApi {
    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @GET("tasks")
    fun fetchTasks(@Query("project_id") id: Long?, @Query("filter")filter: String): Call<MutableList<Task>>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @POST("tasks/{id}/close")
    fun changeStateOfTask(@Path("id") id: Long?): Call<Unit>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @DELETE("task/{id}")
    fun deleteTask(@Path("id") id: Long?): Call<Unit>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )
    @POST("tasks")
    fun createNote(@Body data: Task): Call<Task>
}

