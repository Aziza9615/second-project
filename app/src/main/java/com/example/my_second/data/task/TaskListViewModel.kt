package com.example.my_second.data.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task

class TaskListViewModel: ViewModel() {

    private val repository = TaskRepository.TaskRepositoryImpl()
    var task = mutableListOf<Task>()
    val data: MutableLiveData<MutableList<Task>>?
    val message: MutableLiveData<String>?

    var project: Project? = null

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        fetchAllProjectsTasks()
    }

    fun fetchAllProjectsTasks() {
        repository.fetchAllProjectsTasks(project?.id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message?.value = it.message
                ResponseResultStatus.SUCCESS -> data?.value = it.result
            }
        }
    }
}