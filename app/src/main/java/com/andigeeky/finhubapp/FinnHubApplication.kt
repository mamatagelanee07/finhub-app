package com.andigeeky.finhubapp

import android.app.Application
import com.andigeeky.finhubapp.di.di_modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FinnHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FinnHubApplication)
            androidLogger(Level.ERROR) // we can change this when koin 3.2.0 released & supports kotlin 1.6
            modules(di_modules)
        }
    }
}