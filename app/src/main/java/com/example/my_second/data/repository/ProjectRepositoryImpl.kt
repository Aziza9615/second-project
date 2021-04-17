package com.example.my_second.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.model.Project
import com.example.my_second.data.local.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ProjectRepository {
    fun fetchProjects()
}

class ProjectRepositoryImpl: ProjectRepository {

    val api = RetrofitClient().projectApi

    val data: MutableLiveData<MutableList<Project>>? = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    override fun fetchProjects() {
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                message?.value = t.message
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                 data?.value = response.body()
            }
        })
    }
}