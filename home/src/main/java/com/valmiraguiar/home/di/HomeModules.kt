package com.valmiraguiar.home.di

import com.valmiraguiar.core.navigation.features.HomeFeatureNavigation
import com.valmiraguiar.home.navigation.HomeFeatureNavigationImpl
import org.koin.dsl.module

val homeModules = module {
    single<HomeFeatureNavigation> {
        HomeFeatureNavigationImpl()
    }
}