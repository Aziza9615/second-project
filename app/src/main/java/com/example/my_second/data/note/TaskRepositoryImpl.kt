package com.example.my_second.data.note

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.api.TaskApi
import com.example.my_second.data.local.ResponseResult
import com.example.my_second.data.model.PrimaryColor
import com.example.my_second.data.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface TaskRepository {
    fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>>
    fun createNote(dto: Task): MutableLiveData<ResponseResult<Task>>
    fun closeNote(id: Long?): MutableLiveData<ResponseResult<Boolean>>
    fun deleteTask(id: Long?)
}

class TaskRepositoryImpl(private val api: TaskApi) : TaskRepository {

    override fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Task>>> =
                MutableLiveData()
        api.fetchTasks(id, "Посмотреть все").enqueue(object : Callback<MutableList<Task>> {

            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                data.value = if (response.isSuccessful) ResponseResult.success(response.body())
                        else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun createNote(dto: Task): MutableLiveData<ResponseResult<Task>> {
        val data: MutableLiveData<ResponseResult<Task>> = MutableLiveData(ResponseResult.loading())
        api.createNote(dto).enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                data.value =
                        if (response.isSuccessful) ResponseResult.success(response.body())
                        else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun closeNote(id: Long?) : MutableLiveData<ResponseResult<Boolean>> {
        val data = MutableLiveData<ResponseResult<Boolean>>(ResponseResult.loading())
        api.changeStateOfTask(id).enqueue(object : Callback<Unit> {
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

    override fun deleteTask(id: Long?) {
        api.deleteTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            }
        })
    }
}