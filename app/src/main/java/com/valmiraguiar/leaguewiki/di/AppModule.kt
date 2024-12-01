package com.valmiraguiar.leaguewiki.di

import com.valmiraguiar.leaguewiki.navigation.DefaultNavigator
import org.koin.dsl.module

val applicationModules = module {
    single {
        DefaultNavigator(get(), get())
    }
}