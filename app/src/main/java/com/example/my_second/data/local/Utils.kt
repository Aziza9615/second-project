package com.example.my_second.data.local

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String?) {
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun display(): String {
    return "Companion object Extensions"
}

fun main (args: Array<String>) {
val list = mutableListOf(6,7,9)
    print("The the List $list ")
}
