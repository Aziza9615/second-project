package com.example.my_second.data.task

import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.local.RetrofitClient
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(var callback: RequestResult) {

    val api = RetrofitClient().tasksApi

    fun fetchAllProjectsTasks(id: Long?) {
        api.fetchTasks(id).enqueue(object : Callback<MutableList<Task>> {
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else callback.onFailure(response.message())
            }
        })
    }

}