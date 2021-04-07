 package com.example.my_second.data.task

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.local.RequestResult
import com.example.my_second.data.local.showToast
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.repository.TaskRepository
import com.example.my_second.data.task.TaskListActivity.Companion.start
import com.example.my_second.data.viewModel.task.TaskListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.NonCancellable.start

 class TaskListActivity : AppCompatActivity(), RequestResult, TaskAdapter.ClickListener {

    private var project = Project()

    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
        getIntentData()
        setupRecyclerView()
        subscribeToLiveData()
    }

    private fun getIntentData() {
        project = intent.getSerializableExtra(PROJECT_KEY) as Project
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun subscribeToLiveData() {
        viewModel.data?.observe(this,   Observer {
            if (it != null) adapter.addItems(it)
        })
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

    }

    override fun onRemoveItem(item: Task, position: Int) {
        //repository.deleteTask(item.id)
    }

    companion object {
        const val PROJECT_KEY = "PROJECT_KEY"

        fun start(context: Context, item: Project) {
            val intent = Intent(context, TaskListActivity::class.java)
            intent.putExtra(PROJECT_KEY, item)
            context.startActivity(intent)
        }
    }
}