package com.example.my_second.data.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.model.Project
import com.example.my_second.data.task.RequestResult
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : AppCompatActivity(), RequestResult {

    private lateinit var adapter: ProjectAdapter
    private lateinit var repository: ProjectRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onFailure(t: Throwable) {
    }

    override fun <T> onSuccess(result: T) {
    }

    override fun onItemClick(item: Project) {
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }
}
