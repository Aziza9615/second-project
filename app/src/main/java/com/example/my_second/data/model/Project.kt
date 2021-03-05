package com.example.my_second.data.model

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class Project(
    var id: Long? = null,
    var color: Int? = null,
    var name: String? = null,
    @SerializedName("comment_count")
    var commentId: Int? = null,
    var shared: Boolean? = false,
    var favorite: Boolean? = false,
    @SerializedName("sync_Id")
    var syncId: Int? = null,
    @SerializedName("inbox_project")
    var inboxProject: Boolean? = false
)