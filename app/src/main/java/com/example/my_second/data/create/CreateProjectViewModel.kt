package com.example.my_second.data.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.project.ProjectRepositoryImpl

class CreateProjectViewModel : ViewModel() {

    private val repository = ProjectRepositoryImpl()
    val createResult: MutableLiveData<Boolean>?
    val message: MutableLiveData<String>?

    init {
        createResult = MutableLiveData()
        message = MutableLiveData()
        subscribeToResult()
        subscribeToMessage()
    }

    private fun subscribeToResult() {
        repository.createResult?.observeForever {
            createResult?.postValue(it)
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }

    fun createProject(name: String) {
        if (name.isEmpty()) {
            message?.postValue("Имя проекта не может быть пустым ")
            return
        }
        repository.createProject(name)
    }
}
