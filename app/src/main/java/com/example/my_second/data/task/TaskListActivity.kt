package com.example.my_second.data.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.local.showToast
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.project.ProjectActivity
import com.example.my_second.data.repository.TaskRepository
import kotlinx.android.synthetic.main.activity_main.*

class TaskListActivity : AppCompatActivity(), RequestResult, TaskAdapter.ClickListener {

    private var project = Project()

    private lateinit var adapter: TaskAdapter
    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        getIntentData()
        setupRecyclerView()
        setupRepository()
        fetchData()
    }

    private fun getIntentData() {
        project = intent.getSerializableExtra(ProjectActivity.PROJECT_KEY) as Project
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupRepository() {
        repository = TaskRepository(this)
    }

    private fun fetchData() {
        repository.fetchAllProjectsTasks(project.id)
    }

    override fun <T> onSuccess(result: T) {
        if (result is String) {
            printSuccessedRequest(result)
        }else if (result is MutableList<*>) {
            val data = result as MutableList<Task>
            adapter.addItems(data)
        }
    }

    override fun onFailure(t: String?) {
        showToast(t)
    }

    fun printSuccessedRequest(message: String){
        when(message) {
           "changed  state of task" -> showToast("Task завершен")
            "deleted task" -> showToast("Task удален")
        }
    }

    override fun onItemClick(item: Task) {
    }

    override fun onCheckedClick(item: Task) {
        repository.changeStateOfTask(item. id)
    }

    override fun onRemoveItem(item: Task, position: Int) {
        repository.deleteTask(item.id)
    }
}