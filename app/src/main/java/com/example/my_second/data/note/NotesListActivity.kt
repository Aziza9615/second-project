 package com.example.my_second.data.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.base.BaseActivity
import com.example.my_second.data.base.NoteEvent
import com.example.my_second.data.model.Project
import com.example.my_second.data.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_bottom_tab.*


 class NotesListActivity : BaseActivity<NoteListViewModel>(R.layout.activity_notes_list, NoteListViewModel::class), NotesAdapter.ClickListener {

     private lateinit var adapter: NotesAdapter
     private lateinit var dialog: AlertDialog

     override fun setupViews() {
         getIntentData()
         setupRecyclerView()
         addAction()
     }

     private fun getIntentData() {
         viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project?
     }

     private fun setupRecyclerView() {
         adapter = NotesAdapter(this)
         recycler_view.layoutManager = LinearLayoutManager(this)
         recycler_view.adapter = adapter
     }

     override fun subscribeToLiveData() {
         subscribeToData()
     }

     private fun subscribeToData() {
         viewModel.event.observe(this, Observer {
             when(it) {
                 is NoteEvent.NoteFetched -> adapter.addItems(it.array)
                 is NoteEvent.NoteClosed -> adapter.refreshItems(closedPosition)
                 is NoteEvent.NoteCreated -> {
                     dialog.dismiss()
                     viewModel.fetchAllProjectsTasks()
                 }
             }
         })
     }

     override fun onItemClick(item: Task) {
         TODO("Not yet implemented")
     }

     var closedPosition: Int = 0
     override fun onCheckedClick(item: Task, position: Int) {
         viewModel.closeNote(item.id)
         closedPosition = position
     }

     override fun onRemoveItem(item: Task, position: Int) {
         TODO("Not yet implemented")
     }

     private fun addAction() {
         btn_add.setOnClickListener {
             showCreateNoteDialog()
         }
     }

     private fun showCreateNoteDialog() {
         val layoutInflater = LayoutInflater.from(this)
         val view = layoutInflater.inflate(R.layout.dialog_create_notes, null)
         dialog = AlertDialog.Builder(this).create()
         dialog.setView(view)
         dialog.setCancelable(true)

         view.findViewById<Button>(R.id.dialog_add_btn).setOnClickListener {
             val text = view.findViewById<EditText>(R.id.et_input_title).text.toString()
             viewModel.createNote(text)
         }

         view.findViewById<Button>(R.id.dialog_cancel_btn).setOnClickListener {
             dialog.dismiss()
         }
         dialog.show()
     }

     companion object {
         const val PROJECT_KEY = "PROJECT_KEY"

         fun instance(context: Context, item: Project) {
             val intent = Intent(context, NotesListActivity::class.java)
             intent.putExtra(PROJECT_KEY, item)
             context.startActivity(intent)
         }
     }
 }