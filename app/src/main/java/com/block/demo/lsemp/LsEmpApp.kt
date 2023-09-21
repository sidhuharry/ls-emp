package com.block.demo.lsemp

import android.app.Application
import android.util.Log
import com.block.demo.lsemp.di.appModule
import com.block.demo.lsemp.di.networkModule
import com.block.demo.lsemp.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LsEmpApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Starting the app with koin.")
        // koin plugs in here
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LsEmpApp)
            modules(networkModule, repoModule, appModule)
        }
    }

    companion object {
        private const val TAG = "LsEmpApp"
    }
}
