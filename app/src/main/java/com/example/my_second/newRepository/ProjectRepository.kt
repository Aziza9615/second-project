package com.example.my_second.newRepository

//import com.example.my_second.data.local.App
//import com.example.my_second.data.model.Project
//import com.example.my_second.data.model.Task
//import com.example.my_second.data.network.RetrofitClient
//import retrofit2.Call
//import retrofit2.Callback
//
//class ProjectRepository {
//
//    private var api = RetrofitClient().ProjectApi
//
//    private val database = App.getDatabase().TaskDao()
//
//    fun fetchProject() {
//        api.fetchProject().enqueue(object : Call<MutableList<Project>> {})
//            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
//                return callback.onFailure(t)
//            }
//        }