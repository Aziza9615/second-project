package com.example.my_second.data.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.my_second.R
import com.example.my_second.data.base.BaseAdapter
import com.example.my_second.data.base.BaseViewHolder
import com.example.my_second.data.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private var listener: TaskListActivity) : BaseAdapter() {

    private var items = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as TasksViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
        holder.itemView.cb_task.setOnClickListener {
            listener.onCheckedClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onRemoveItem(item, position)
            true
        }
    }

    fun addItems(data: MutableList<Task>) {
        items = data
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: Task)
        fun onCheckedClick(item: Task)
        fun onRemoveItem(item: Task, position: Int)
    }
}

class TasksViewHolder(itemView: View) : BaseViewHolder(itemView) {
    fun bind(item: Task) {
        itemView.tv_task.text = item.content
        val stateOfTask = item.completed?: false
        itemView.cb_task.isChecked = stateOfTask
    }
}