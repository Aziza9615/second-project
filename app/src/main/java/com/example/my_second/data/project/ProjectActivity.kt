package com.example.my_second.data.project

import android.content.Intent
import android.os.Handler
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.task.TaskListActivity
import com.example.my_second.data.model.Project
import com.example.my_second.data.task.TaskListActivity.Companion.PROJECT_KEY
import com.example.my_second.data.viewModel.ProjectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : BaseActivity<ProjectViewModel>(R.layout.activity_main, ProjectViewModel::class.java), ProjectAdapter.ClickListener {

    lateinit var adapter: ProjectAdapter

    override fun setupViews() {
        setupRecyclerView()
        setupSearchView()
        addAction()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
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
                        adapter.addItems(viewModel.project)
                    } else {

                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<Project>()
                        viewModel.project.forEach { if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it) }
                        adapter.addItems(filtered)

                    }
                }, 800)
                return false
            }
        })
    }

    private fun addAction() {
        btn_add.setOnClickListener {
            CreateProjectActivity.instance(this)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.data?.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.instance(this, item)
        var intent = Intent(this, CreateProjectActivity::class.java)
        intent.putExtra(ITEM_KEY, item)
        startActivity(intent)
    }

    companion object {
        const val ITEM_KEY = "ITEM_KEY"
    }
}