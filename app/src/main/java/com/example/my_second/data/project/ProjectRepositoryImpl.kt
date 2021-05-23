package com.example.my_second.data.project

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
    fun createProject(name: String, color: Int?): MutableLiveData<ResponseResult<Project>>
    fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>>
    fun closeNote(id: Long?): MutableLiveData<ResponseResult<Boolean>>
}

class ProjectRepositoryImpl(private val api: ProjectApi) : ProjectRepository {

    override fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Project>>>
                = MutableLiveData(ResponseResult.loading())
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }
            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                data.value = if (response.isSuccessful) ResponseResult.success(response.body())
                else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun closeNote(id: Long?) : MutableLiveData<ResponseResult<Boolean>> {
        val data = MutableLiveData<ResponseResult<Boolean>>(ResponseResult.loading())
        api.changeStateOfProjects(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                data.value = ResponseResult.loading(t.message)

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                data.value = if (response.isSuccessful) ResponseResult.success(true)
                else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>> {
        val data: MutableLiveData<ResponseResult<Int>>
                = MutableLiveData(ResponseResult.loading())
        api.deleteProject(id).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) data.value = ResponseResult.success(response.code())
            }
        })
        return data
    }

    override fun createProject(name: String, color: Int?): MutableLiveData<ResponseResult<Project>> {
        val project = Project(name = name, color = color)
        val data: MutableLiveData<ResponseResult<Project>>
                = MutableLiveData(ResponseResult.loading())
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
