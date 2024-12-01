package com.valmiraguiar.core.di

import com.valmiraguiar.core.navigation.DefaultNavigator
import org.koin.dsl.module

val applicationModules = module {
    single {
        DefaultNavigator(get(), get())
    }
}