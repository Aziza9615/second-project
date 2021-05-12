package com.example.my_second.data.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.repository.TaskRepository
import com.example.my_second.data.repository.TaskRepositoryImpl

class TaskListViewModel(private val repository: TaskRepositoryImpl) : BaseViewModel() {

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    var project: Project? = null

    init {
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
