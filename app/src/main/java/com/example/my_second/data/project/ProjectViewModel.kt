package com.example.my_second.data.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.repository.ProjectRepository
import com.example.my_second.data.repository.ProjectRepositoryImpl

class ProjectViewModel(private val repository: ProjectRepositoryImpl) : BaseViewModel() {
    var project = mutableListOf<Project>()
    val data = MutableLiveData<MutableList<Project>>()

    init {
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
}

fun main() {
    val car = Car(Engine(Gazoline()), Body(), Driver(License(19, "KG")))
}

class Car(engine: Engine, body: Body, driver: Driver) {

}

class Body() {

}

class Driver(license: License) {

    fun getClassOfLicense() {

    }
}

class License(age: Int, gos: String)

class Engine(fuel: Fuel) {

}

open class Fuel() {

}

class Gazoline() : Fuel() {

}

class Electrosity : Fuel() {

}

// домашнее задание в 80 уроке , не забудь
// 20 50 удаление из постмана проект