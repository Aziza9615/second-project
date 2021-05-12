package com.example.my_second.data.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.project.ProjectRepositoryImpl

class ProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel() {
    var project = mutableListOf<Project>()
    val data = MutableLiveData<MutableList<Project>>()

    fun fetchProjects() {
        repository.fetchProjects().observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    data.value = it.result
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

//fun main() {
//    val car = Car(Driver.Engine(Driver.Gazoline()), Body(), Driver(Driver.License(19, "KG")))
//}
//
//class Car(engine: Driver.Engine, body: Body, driver: Driver) {
//
//}
//
//class Body() {
//
//}
//
//class Driver(license: License) {
//
//    fun getClassOfLicense() {
//    }
//
//    class License(age: Int, gos: String)
//
//    class Engine(fuel: Fuel) {
//
//    }
//
//    open class Fuel() {
//
//    }
//
//    class Gazoline() : Fuel() {
//
//    }
//
//    class Electrosity : Fuel() {

     // домашнее задание в 80 уроке , не забудь
    // 20 50 удаление из постмана проект
