package com.example.my_second.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.model.Project
import com.example.my_second.data.repository.ProjectRepository
import com.example.my_second.data.repository.ProjectRepositoryImpl

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepositoryImpl()
    var project = mutableListOf<Project>()
    val data: MutableLiveData<MutableList<Project>>?
    val message: MutableLiveData<String>?

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchProjects()
    }

    private fun subscribeToData() {
        repository.data?.observeForever {
            project = it
            data?.value = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it.toString()
        }
    }
}