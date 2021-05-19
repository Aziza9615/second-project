package com.example.my_second.data.create


import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.project.ProjectRepositoryImpl

class CreateProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel() {
    val createResult = MutableLiveData<Boolean>()

    fun createProject(name: String, color: Int?) {
        if (name.isEmpty()) {
            message.postValue("Имя проекта не может быть пустым")
            return
        }
        if (color == null) {
            message.postValue("Цвет проекта не выбран")
            return
        }
        repository.createProject(name, color).observeForever {
                when (it.status) {

                    ResponseResultStatus.SUCCESS -> {
                        createResult.value = it.result != null
                        if (it.result != null) message.value = "Проект успешшно создан"
                        else message.value = "ошибка при создании проекта"
                    }
                    ResponseResultStatus.ERROR -> message.value = it.message
                }
            }
        }
    }

