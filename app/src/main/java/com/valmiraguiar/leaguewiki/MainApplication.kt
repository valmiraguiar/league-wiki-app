package com.valmiraguiar.leaguewiki

import android.app.Application
import com.valmiraguiar.detail.di.detailModules
import com.valmiraguiar.core.di.coreModules
import com.valmiraguiar.home.di.homeModules
import com.valmiraguiar.leaguewiki.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    applicationModules,
                    coreModules,
                    homeModules,
                    detailModules
                )
            )
        }
    }
}