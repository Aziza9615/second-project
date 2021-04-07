package com.example.my_second.data.local

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String?) {
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun View.visible() {

}

