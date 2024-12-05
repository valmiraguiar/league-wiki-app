package com.valmiraguiar.core.di

import com.valmiraguiar.core.api.OkHttpBuilder
import com.valmiraguiar.core.api.RetrofitBuilder
import com.valmiraguiar.core.navigation.DefaultNavigator
import org.koin.dsl.module

val coreModules = module {
    single {
        DefaultNavigator(get(), get())
    }

    single { OkHttpBuilder().build() }

    single { RetrofitBuilder(get()).build(get()) }
}