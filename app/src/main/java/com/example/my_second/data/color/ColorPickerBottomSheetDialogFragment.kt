package com.example.my_second.data.color

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_second.R
import com.example.my_second.data.color.ColorType.colors
import com.example.my_second.data.local.showToast
import com.example.my_second.data.model.PrimaryColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_color_picker.*

interface PickerColor {
    fun choosedColor(colors: MutableList<PrimaryColor>)
}

class ColorPickerBottomSheetDialogFragment(private val listener: PickerColor) : BottomSheetDialogFragment(),
        ColorAdapter.ClickListener {

    private var pickerColors = colors
    lateinit var adapter: ColorAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_color_picker, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ColorAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(requireContext(), COUNT_OF_ROW)
        adapter.addItems(pickerColors)
    }

    override fun onItemClick(item: PrimaryColor, position: Int) {
        pickerColors.forEach { it.isSelected = false }
        pickerColors[position].isSelected = true
        adapter.addItems(pickerColors)
    }

    companion object {
        const val COUNT_OF_ROW = 6
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialog.cancel()
        listener.choosedColor(pickerColors)
    }
}