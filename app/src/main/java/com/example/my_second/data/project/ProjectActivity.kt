package com.example.my_second.data.project

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.task.TaskListActivity
import com.example.my_second.data.model.Project
import com.example.my_second.data.viewModel.ProjectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : BaseActivity<ProjectViewModel>(R.layout.activity_main, ProjectViewModel::class.java), ProjectAdapter.ClickListener {

    lateinit var adapter: ProjectAdapter

    override fun setupViews() {
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupSearchView() {

    }

    override fun subscribeToLiveData() {
        viewModel.data?.observe(this, Observer {
            if (it != null) adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.start(this, item)
    }
}