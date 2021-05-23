package com.example.my_second.data.project

import com.example.my_second.data.base.BaseEvent
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.base.NoteEvent
import com.example.my_second.data.base.ProjectEvent
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project


class ProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel<BaseEvent>() {

    var project: MutableList<Project>? = mutableListOf()

    init {
        fetchProjects()
    }

    fun fetchProjects() {
        repository.fetchProjects().observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    project = it.result
                    event.value = ProjectEvent.ProjectFetched(project)
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }

    fun deleteProject(id: Long?) {
        repository.deleteProject(id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    handleResult(it.result)
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }
    private fun handleResult(code: Int?) {
        if (code == 204) message.value = "Проект успешно удален"
    }
}
