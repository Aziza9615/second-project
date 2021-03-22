package com.example.my_second.data.local

interface RequestResult {
    fun <T>onSuccess(result : T)
    fun onFailure(t: String?)
}