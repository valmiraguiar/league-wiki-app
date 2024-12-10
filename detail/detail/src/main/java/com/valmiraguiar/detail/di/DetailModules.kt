package com.valmiraguiar.detail.di

import com.valmiraguiar.core.navigation.features.ChampionDetailFeatureNavigation
import com.valmiraguiar.detail.navigation.ChampionDetailFeatureNavigationImpl
import org.koin.dsl.module

val detailModules = module {
    single<ChampionDetailFeatureNavigation> {
        ChampionDetailFeatureNavigationImpl()
    }
}