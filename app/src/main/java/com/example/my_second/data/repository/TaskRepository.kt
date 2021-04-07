package com.example.my_second.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.local.RetrofitClient
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository {

    val api = RetrofitClient().tasksApi

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    fun fetchTasks() {
        api.fetchTasks().enqueue(object : Callback<MutableList<Task>> {
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                message?.value = t.message
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                data?.value = response.body()
            }
        })
    }

    fun changeStateOfTask(id: Long?) {
        api.changeStateOfTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
               message?.value = t.message
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
               // data?.value = response.body()
            }
        })
    }

    fun deleteTask(id: Long?) {
        api.deleteTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                message?.value = t.message
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
               // data?.value = response.body()
            }
        })
    }
}