package com.example.my_second.data.project

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.view.Menu
import android.view.MenuItem
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.local.showToast
import com.example.my_second.data.model.Project
import com.example.my_second.data.task.TaskListActivity.Companion.PROJECT_KEY
import com.example.my_second.data.viewModel.CreateProjectViewModel
import kotlinx.android.synthetic.main.activity_create_project.*

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>(
    R.layout.activity_create_project,
    CreateProjectViewModel::class.java
) {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_project, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> showToast("CLICK CREATE")
        }
        return true
    }

    override fun setupViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.create_project)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val item = intent.getSerializableExtra(ProjectActivity.ITEM_KEY) as Project
    }

    override fun subscribeToLiveData() {

    }

    companion object {
        fun instance(context: Context) {
            val intent = Intent(context, CreateProjectActivity::class.java)
            context.startActivity(intent)
        }
    }
}