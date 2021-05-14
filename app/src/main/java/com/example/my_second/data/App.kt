package com.example.my_second.data

import android.app.Application
import com.example.my_second.data.di.networkRepository
import com.example.my_second.data.di.repositoryModule
import com.example.my_second.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            inject()
        }
    }

    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(listOf(viewModelModule, repositoryModule, networkRepository))
    }
}