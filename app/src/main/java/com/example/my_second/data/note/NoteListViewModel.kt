package com.example.my_second.data.note

import androidx.lifecycle.MutableLiveData
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task

class NoteListViewModel(private val repository: TaskRepositoryImpl) : BaseViewModel() {

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    var project: Project? = null
    var noteCreating = MutableLiveData<Boolean>()

    init {
        fetchAllProjectsTasks()
    }

    fun fetchAllProjectsTasks() {
        repository.fetchAllProjectsTasks(project?.id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message.value = it.message
                ResponseResultStatus.SUCCESS -> data?.value = it.result
            }
        }
    }

    fun createNote(text: String) {
        val data = Task(projectId = project?.id, content = text)
        repository.createNote(data).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message.value = it.message
                ResponseResultStatus.SUCCESS -> {
                    if (it.result != null) message.value = "Заметка успешно создана"
                    else message.value = "Ошибка при создании заметки"
                    noteCreating.value = it.result != null
                }
            }
        }
    }
}