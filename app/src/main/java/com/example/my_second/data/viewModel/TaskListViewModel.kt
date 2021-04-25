package com.example.my_second.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.repository.TaskRepository
import com.example.my_second.data.repository.TaskRepositoryImpl

class TaskListViewModel: ViewModel() {

    private val repository = TaskRepositoryImpl()
    val data: MutableLiveData<MutableList<Task>>?
    val message: MutableLiveData<String>?

    var project: Project? = null

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchTasks(project?.id)
    }

    fun subscribeToData() {
        repository.data?.observeForever {
            data?.value = it
        }
    }

    fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it.toString()
        }
    }
}