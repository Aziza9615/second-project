package com.example.my_second.newRepository

import com.example.my_second.data.local.App.Companion.getDatabase
import com.example.my_second.data.model.Task
import com.example.my_second.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface RequestResult {
    fun onFailure(t: Throwable)
    fun <T>onSuccess(result: T)
}

class TaskRepository(private val callback: RequestResult) {

    private var api = RetrofitClient().TaskApi

    private val database = getDatabase().TaskDao()

    fun getAllTasks() {
        callback.onSuccess(database.getAllTasks())
        api.getAllTasks().enqueue(object : Callback<MutableList<Task>> {
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                return callback.onFailure(t)
            }

            override fun onResponse(
                    call: Call<MutableList<Task>>,
                    response: Response<MutableList<Task>>
            ) {
                return if (response.body() != null) {
                    val data = response.body()
                    database.InsertTask(data)
                    callback.onSuccess(data)
                } else {
                    callback.onFailure(Throwable("error"))
                }
            }
        })
    }
}

private fun <E> MutableList<E>.enqueue(callback: Callback<MutableList<E>>) {
}

