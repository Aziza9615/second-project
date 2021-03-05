package com.example.my_second.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val assigner: Int,
    val project_id: Int,
    val selection_id: Int,
    val order: Int,
    val content: String,
    val completed: Boolean = false,
    val priority: Int,
    val comment_count: Int,
    val creator: Int,
    val created: String,
    val url: String
)