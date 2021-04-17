package com.example.my_second.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.local.RetrofitClient
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface TaskRepository {
    fun fetchTasks(id: Long?)
    fun changeStateOfTask(id: Long?)
    fun deleteTask(id: Long?)

}
class TaskRepositoryImpl: TaskRepository {

    val api = RetrofitClient().tasksApi

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()

    override fun fetchTasks(id: Long?) {
        api.fetchTasks(id).enqueue(object : Callback<MutableList<Task>> {
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                message?.value = t.message
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                if (response.isSuccessful) data?.value = response.body()
                else  message?.value = response.message()
            }
        })
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