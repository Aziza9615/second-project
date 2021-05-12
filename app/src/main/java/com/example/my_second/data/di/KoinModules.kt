package com.example.my_second.data.di

import com.example.my_second.data.create.CreateProjectViewModel
import com.example.my_second.data.local.*
import com.example.my_second.data.project.ProjectRepositoryImpl
import com.example.my_second.data.project.ProjectViewModel
import com.example.my_second.data.task.TaskListViewModel
import com.example.my_second.data.task.TaskRepository
import com.example.my_second.data.task.TaskRepositoryImpl
import get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CreateProjectViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { TaskListViewModel(get()) }
}

val repositoryModule = module {
    factory { ProjectRepositoryImpl(get()) }
    factory { TaskRepositoryImpl(get()) }
}

val networkRepository = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideProjectApi(get()) }
    single { provideTaskApi(get()) }
}