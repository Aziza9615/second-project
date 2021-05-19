package com.example.my_second.data.create

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.color.ColorPickerBottomSheetDialogFragment
import com.example.my_second.data.color.PickerColorListener
import com.example.my_second.data.local.showToast
import com.example.my_second.data.local.visible
import com.example.my_second.data.model.PrimaryColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import kotlinx.android.synthetic.main.item_color.*
import org.koin.android.ext.android.inject

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>(R.layout.activity_create_project, CreateProjectViewModel::class), PickerColorListener {

    var selectedColor: PrimaryColor? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_project, menu)
        supportActionBar?.hide()
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
        viewModel.createProject(projectName, selectedColor?.id)
    }

    override fun setupViews() {
        setupToolbar()
        setupColorPicker()
    }

    private fun setupToolbar() {
        supportActionBar?.title = resources.getString(R.string.create_project)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupColorPicker() {
        btn_select_color.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                    ColorPickerBottomSheetDialogFragment()
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(
                    supportFragmentManager,
                    bottomSheetDialogFragment.tag)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.createResult.observe(this, Observer {
            if (it == true) finish()
        })
    }

    companion object {
        fun instance(context: Context) {
            val intent = Intent(context, CreateProjectActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun choosedColor(colors: MutableList<PrimaryColor>) {
        colors.forEach { if (it.isSelected) setupSelectedViews(it) }
    }

    private fun setupSelectedViews(item: PrimaryColor) {
        selectedColor = item
        color_view.visible()
        color_view.background.setColorFilter(Color.parseColor(item.hexCode), PorterDuff.Mode.SRC_ATOP)
        btn_select_color.text = "Change color"
    }
}