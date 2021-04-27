package com.example.my_second.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "task")
data class Task (
        @PrimaryKey(autoGenerate = false)
        val id: Long? = null,
        var name: String? = null,
        val assigner: Int? = null,
        @SerializedName("project_id")
        val projectId: Long? = null,
        @SerializedName("section_id")
        val sectionId: Int? = null,
        val order: Int? = null,
        val content: String? = null,
        val completed: Boolean? = false,
//        @SerializedName("label_ids")
        //val labelIds: MutableList<Int>,
        val priority: Int? = null,
        @SerializedName("comment_count")
        val commentCount: Int? = null,
        val creator: Int? = null,
        val created: String? = null,
        val url: String? = null
)