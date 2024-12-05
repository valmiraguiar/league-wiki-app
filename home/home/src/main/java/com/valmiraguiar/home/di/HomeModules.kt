package com.valmiraguiar.home.di

import com.valmiraguiar.core.api.HttpConfig
import com.valmiraguiar.core.api.create
import com.valmiraguiar.core.navigation.features.HomeFeatureNavigation
import com.valmiraguiar.domain.repository.ChampionsRepository
import com.valmiraguiar.home.data.api.HomeApi
import com.valmiraguiar.home.data.repository.ChampionsRepositoryImpl
import com.valmiraguiar.home.navigation.HomeFeatureNavigationImpl
import org.koin.dsl.module

val homeModules = module {
    single<HomeFeatureNavigation> {
        HomeFeatureNavigationImpl()
    }

    factory<HomeApi> {
        get<HttpConfig>().create()
    }

    single<ChampionsRepository> {
        ChampionsRepositoryImpl(get(), get())
    }
}