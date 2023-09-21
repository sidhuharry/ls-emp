package com.block.demo.lsemp.di

import com.block.demo.lsemp.network.api.GetEmployeeApi
import com.block.demo.lsemp.network.service.EmployeeDirService
import com.block.demo.lsemp.network.util.NetworkUtils
import com.block.demo.lsemp.repo.EmployeeDetailsRepo
import com.block.demo.lsemp.ui.home.HomeActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        createEmployeeDetailsApi()
    }

    single {
        NetworkUtils()
    }

    single {
        EmployeeDirService(get(), get())
    }
}

val repoModule = module {
    single {
        EmployeeDetailsRepo(get())
    }
}

val appModule = module {
    viewModel {
        HomeActivityViewModel(get())
    }
}

private fun okHttpClient() = OkHttpClient.Builder().build()

fun createEmployeeDetailsApi(): GetEmployeeApi {
    val retrofit =
        Retrofit.Builder().baseUrl("https://s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient()).build()
    return retrofit.create(GetEmployeeApi::class.java)
}
