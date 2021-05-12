package com.example.my_second.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.api.ProjectApi
import com.example.my_second.data.local.ResponseResult
import com.example.my_second.data.model.Project
import com.example.my_second.data.local.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ProjectRepository {
    fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>>
    fun createProject(name: String): MutableLiveData<ResponseResult<Project>>
}

class ProjectRepositoryImpl(private val api: ProjectApi) : ProjectRepository {

    override fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Project>>>
                = MutableLiveData()
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun createProject(name: String): MutableLiveData<ResponseResult<Project>> {
        val project = Project(name = name, color = 38)
        val data: MutableLiveData<ResponseResult<Project>>
                = MutableLiveData()
        api.createProject(project).enqueue(object : Callback<Project> {
            override fun onFailure(call: Call<Project>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

}