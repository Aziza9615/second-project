package com.example.my_second.data.note

import com.example.my_second.data.base.BaseEvent
import com.example.my_second.data.base.BaseViewModel
import com.example.my_second.data.base.NoteEvent
import com.example.my_second.data.local.ResponseResultStatus
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task

class NoteListViewModel(private val repository: TaskRepositoryImpl) : BaseViewModel<BaseEvent>() {

    var project: Project? = null

    init {
        fetchAllProjectsTasks()
    }

    fun fetchAllProjectsTasks() {
        repository.fetchAllProjectsTasks(project?.id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message.value = it.message
                ResponseResultStatus.SUCCESS -> event.value = NoteEvent.NoteFetched(it.result)
            }
        }
    }

    fun createNote(text: String) {
        val data = Task(projectId = project?.id, content = text)
        repository.createNote(data).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message.value = it.message
                ResponseResultStatus.SUCCESS -> {
                    if (it.result != null) {
                        message.value = "Заметка успешно создана"
                        event.value = NoteEvent.NoteCreated
                    } else {
                        message.value = "Ошибка при создании заметки"
                    }
                }
            }
        }
    }

    fun closeNote(id: Long?) {
        repository.closeNote(id).observeForever {
            when(it.status) {
                ResponseResultStatus.SUCCESS -> event.value = NoteEvent.NoteClosed
                ResponseResultStatus.ERROR -> message.value = it.message
            }
        }
    }
}
