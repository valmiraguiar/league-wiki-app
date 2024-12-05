package com.valmiraguiar.core.di

import com.valmiraguiar.core.api.OkHttpBuilder
import com.valmiraguiar.core.api.RetrofitBuilder
import com.valmiraguiar.core.infrastructure.SafeApiCaller
import com.valmiraguiar.core.navigation.DefaultNavigator
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModules = module {
    single {
        DefaultNavigator(get(), get())
    }

    single { OkHttpBuilder().build() }

    single { RetrofitBuilder(get()).build(get()) }

    single { SafeApiCaller(Dispatchers.IO) }
}