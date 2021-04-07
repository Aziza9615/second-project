package com.example.my_second.data.viewModel.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.model.Task
import com.example.my_second.data.repository.TaskRepository

class TaskListViewModel: ViewModel() {

    private val repository = TaskRepository()
    val data: MutableLiveData<MutableList<Task>>?
    val message: MutableLiveData<String>?

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchTasks()
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