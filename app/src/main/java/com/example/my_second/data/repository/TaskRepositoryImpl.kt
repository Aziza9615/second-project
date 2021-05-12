package com.example.my_second.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.api.TaskApi
import com.example.my_second.data.local.ResponseResult
import com.example.my_second.data.local.RetrofitClient
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface TaskRepository {
    fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>>
    fun changeStateOfTask(id: Long?)
    fun deleteTask(id: Long?)
}

class TaskRepositoryImpl(private val api: TaskApi) : TaskRepository {

    override fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Task>>> =
            MutableLiveData()
        api.fetchTasks(id).enqueue(object : Callback<MutableList<Task>> {

            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(
                call: Call<MutableList<Task>>,
                response: Response<MutableList<Task>>
            ) {
                data.value =
                    if (response.isSuccessful) ResponseResult.success(response.body())
                    else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun changeStateOfTask(id: Long?) {
        api.changeStateOfTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

            }
        })
    }

    override fun deleteTask(id: Long?) {
        api.deleteTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            }
        })
    }
}
