package com.example.my_second.data.local

interface RequestResult {
    fun onFailure(t: Throwable)
    fun <T>onSuccess(result : T)
}