 package com.example.my_second.data.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

 class TaskListActivity : AppCompatActivity(), TaskAdapter.ClickListener {

     val viewModel by inject<TaskListViewModel>()

     private lateinit var adapter: TaskAdapter
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_task_list)
         getIntentData()
         setupRecyclerView()
         subscribeToLiveData()
     }

     private fun getIntentData() {
         viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project?
     }

     private fun setupRecyclerView() {
         adapter = TaskAdapter(this)
         recycler_view.layoutManager = LinearLayoutManager(this)
         recycler_view.adapter = adapter
     }

     private fun subscribeToLiveData() {
         viewModel.data?.observe(this, Observer {
             adapter.addItems(it)
         })
     }

     override fun onItemClick(item: Task) {
     }

     override fun onCheckedClick(item: Task) {
//        repository.changeStateOfTask(item.id)
     }

     override fun onRemoveItem(item: Task, position: Int) {

     }

     companion object {
         const val PROJECT_KEY = "PROJECT_KEY"
         fun instance(context: Context, item: Project) {
             val intent = Intent(context, TaskListActivity::class.java)
             intent.putExtra(PROJECT_KEY, item)
             context.startActivity(intent)
         }
     }
 }