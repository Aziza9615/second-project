package com.example.my_second.data.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.project.ProjectActivity
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
        val data = result as MutableList<Task>
        adapter.addItems(data)
    }

    override fun onFailure(t: String?) {
        Toast.makeText(this, t, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(item: Task) {

    }
}