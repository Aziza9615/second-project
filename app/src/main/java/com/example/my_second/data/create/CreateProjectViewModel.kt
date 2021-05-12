package com.example.my_second.data.create


import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.project.ProjectRepositoryImpl

class CreateProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel() {
    val createResult = MutableLiveData<Boolean>()

    fun createProject(name: String) {
        if (name.isEmpty()) {
            message.postValue("Имя проекта не может быть пустым")
            return
        }
        repository.createProject(name)
        repository.createProject(name).observeForever {
            when(it.status) {

               ResponseResultStatus.SUCCESS -> createResult.value = it.result != null
               ResponseResultStatus.ERROR -> message.value = it.message
            }
        }
    }
}
