package com.example.my_second.data.viewModel.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.model.Project
import com.example.my_second.data.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepository()
    val data: MutableLiveData<MutableList<Project>>?
    val message: MutableLiveData<String>?

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchProjects()
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