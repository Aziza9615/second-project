package com.example.my_second.data.api

import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.http.*

interface ProjectApi {
    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @GET("projects")
    fun fetchProjects(): Call<MutableList<Project>>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )
    @POST("projects")
    fun createProject(@Body data: Project): Call<Project>

    @Headers(
            "Authorization: Bearer ef2d47cfd8cdf0197fa7e88333a980cfba52256a",
            "client_Id: fa2ce0543994447f9c0e610a6d5c90ee",
            "client_secret: 2bc37377890345de84a635916aaffa70"
    )

    @DELETE("projects/{id}")
    fun deleteProject(@Path("id") id: Long?): Call<Int>
}