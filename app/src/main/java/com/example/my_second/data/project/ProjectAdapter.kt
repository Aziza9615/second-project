package com.example.my_second.data.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_second.R
import com.example.my_second.data.model.Project

class ProjectAdapter(val listener: ProjectActivity): RecyclerView.Adapter<ProjectViewHolder>() {

    private var items = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    interface ClickListener {
        fun onItemClick(item: Project)
    }
}

class ProjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(item: Project) {
    }
}