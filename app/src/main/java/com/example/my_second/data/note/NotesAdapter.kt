package com.example.my_second.data.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.my_second.R
import com.example.my_second.data.base.BaseAdapter
import com.example.my_second.data.base.BaseViewHolder
import com.example.my_second.data.model.Task
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(private var listener: NotesListActivity) : BaseAdapter() {

    private var items = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
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
            listener.onCheckedClick(item, position)
        }
        holder.itemView.setOnLongClickListener {
            listener.onRemoveItem(item, position)
            true
        }
    }

    fun addItems(data: MutableList<Task>?) {
        data?.let {
            items = it
            notifyDataSetChanged()
        }
    }

    fun refreshItems(position: Int) {
    }

        interface ClickListener {
        fun onItemClick(item: Task)
        fun onCheckedClick(item: Task, position: Int)
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