package com.valmiraguiar.detail.di

import com.azul.domain.business.DetailChampionBusiness
import com.azul.domain.repository.DetailChampionRepository
import com.valmiraguiar.core.api.HttpConfig
import com.valmiraguiar.core.api.create
import com.valmiraguiar.core.navigation.features.ChampionDetailFeatureNavigation
import com.valmiraguiar.detail.data.api.DetailChampionApi
import com.valmiraguiar.detail.data.business.DetailChampionBusinessImpl
import com.valmiraguiar.detail.data.repository.DetailChampionRepositoryImpl
import com.valmiraguiar.detail.navigation.ChampionDetailFeatureNavigationImpl
import com.valmiraguiar.detail.presentation.detail.ChampionDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailModules = module {
    single<ChampionDetailFeatureNavigation> {
        ChampionDetailFeatureNavigationImpl()
    }

    single<DetailChampionApi> {
        get<HttpConfig>().create()
    }

    single<DetailChampionRepository> {
        DetailChampionRepositoryImpl(get(), get())
    }

    single<DetailChampionBusiness> {
        DetailChampionBusinessImpl(get())
    }

    viewModel {
        ChampionDetailViewModel(get(), Dispatchers.IO)
    }
}