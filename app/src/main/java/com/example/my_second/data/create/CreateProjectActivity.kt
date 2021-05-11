package com.example.my_second.data.create

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.color.ColorPickerBottomSheetDialogFragment
import com.example.my_second.data.local.showToast
import com.example.my_second.data.model.Project
import com.example.my_second.data.project.ProjectActivity
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
            R.id.action_create -> createProject()
        }
        return true
    }

    private fun createProject() {
        val projectName = et_input_project_title.text.toString()
        viewModel.createProject(projectName)
    }

    override fun setupViews() {
        setupToolbar()
        setupColorPicker()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.create_project)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupColorPicker() {
        btn_select_color.setOnClickListener {
            ColorPickerBottomSheetDialogFragment().apply {
                show(supportFragmentManager, tag)
            }
        }
    }

    override fun subscribeToLiveData() {
        viewModel.createResult?.observe(this, Observer {
            if (it == true) {
                showToast("Проект успешно создан")
                finish()
            }
        })

        viewModel.message?.observe(this, Observer {
            showToast(it)
        })
    }

    companion object {
        fun instance(context: Context) {
            val intent = Intent(context, CreateProjectActivity::class.java)
            context.startActivity(intent)
            val item = intent.getSerializableExtra(ProjectActivity.ITEM_KEY) as Project
        }
    }
}