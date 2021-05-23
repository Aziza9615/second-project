package com.example.my_second.data.project

import android.os.Handler
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.base.ProjectEvent
import com.example.my_second.data.create.CreateProjectActivity
import com.example.my_second.data.di.ItemSimpleTouch
import com.example.my_second.data.model.Project
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_bottom_tab.*

class ProjectActivity : BaseActivity<ProjectViewModel>(R.layout.activity_main, ProjectViewModel::class), ProjectAdapter.ClickListener {

    lateinit var adapter: ProjectAdapter


    override fun setupViews() {
        setupRecyclerView()
        setupSearchView()
        addAction()
        deleteSwipeAction()
        setupSwipeRefresh()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun deleteSwipeAction() {
        val swipeHandler = object : ItemSimpleTouch(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val item = viewModel.project?.get(position)
                viewModel.deleteProject(item?.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Handler().postDelayed(Runnable {
                    if (newText == "") {
                        viewModel.fetchProjects()
                    } else {

                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<Project>()
                        viewModel.project?.forEach { if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it) }
                        adapter.addItems(filtered)

                    }
                }, 800)
                return false
            }
        })
    }

    private fun setupSwipeRefresh() {
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.fetchProjects()
        }

        swipe_refresh_layout.setColorSchemeResources(
            android.R.color.holo_red_light
        )
    }

    private fun addAction() {
        btn_add.setOnClickListener {
            CreateProjectActivity.instance(this)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is ProjectEvent.ProjectFetched -> {
                    swipe_refresh_layout.isRefreshing = false
                    it.array?.let { array -> adapter.addItems(array) }
                }
            }
        })
    }

    override fun onItemClick(item: Project) {

    }
}