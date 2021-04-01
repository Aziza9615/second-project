package com.example.my_second.data.repository

import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.model.Project
import com.example.my_second.data.local.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepository(var callback: RequestResult) {
    val api = RetrofitClient().projectApi
    fun fetchProjects() {
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                 if (response.body() != null)  callback.onSuccess(response.body())
                 else callback.onFailure("error")
            }
        })
    }
}