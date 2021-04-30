 package com.example.my_second.data.task

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import com.example.my_second.data.viewModel.TaskListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.http2.Http2Reader

 class TaskListActivity : BaseActivity<TaskListViewModel>(R.layout.activity_task_list, TaskListViewModel::class.java), TaskAdapter.ClickListener {

     lateinit var adapter: TaskAdapter

     override fun setupViews() {
         getIntentData()
         setupRecyclerView()
         setupSearchView()
     }

     private fun getIntentData() {
         viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project
     }

     private fun setupRecyclerView() {
         adapter = TaskAdapter(this)
         recycler_view.layoutManager = LinearLayoutManager(this)
         recycler_view.adapter = adapter
     }

     private fun setupSearchView() {
         search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String): Boolean {

                 Handler().postDelayed(Runnable {
                     if (newText == "") {
                         adapter.addItems(viewModel.task)
                     } else {

                         val searchText = newText.toLowerCase()
                         val filtered = mutableListOf<Task>()
                         viewModel.task.forEach { if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it) }
                         adapter.addItems(filtered)

                     }
                 }, 800)
                 return false
             }
         })
     }

     override fun subscribeToLiveData() {
         viewModel.data?.observe(this, Observer {
             if (it != null) adapter.addItems(it)
         })
     }

     companion object {
         const val PROJECT_KEY = "PROJECT_KEY"

         fun instance(context: Context, item: Project) {
             val intent = Intent(context, TaskListActivity::class.java)
             intent.putExtra(PROJECT_KEY, item)
             context.startActivity(intent)
         }
     }

     override fun onItemClick(item: Task) {
         TODO("Not yet implemented")
     }

     override fun onCheckedClick(item: Task) {
         TODO("Not yet implemented")
     }

     override fun onRemoveItem(item: Task, position: Int) {
         TODO("Not yet implemented")
     }
 }