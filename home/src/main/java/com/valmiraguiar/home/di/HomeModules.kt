package com.valmiraguiar.home.di

import com.valmiraguiar.home.navigation.HomeFeatureNavigation
import com.valmiraguiar.home.navigation.HomeFeatureNavigationImpl
import org.koin.dsl.module

val homeModules = module {
    single<HomeFeatureNavigation> {
        HomeFeatureNavigationImpl()
    }
}