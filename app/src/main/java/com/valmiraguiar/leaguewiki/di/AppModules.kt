package com.valmiraguiar.leaguewiki.di

import com.valmiraguiar.core.repository.BaseWebService
import com.valmiraguiar.leaguewiki.BuildConfig
import org.koin.dsl.module

val applicationModules = module {
    single { BaseWebService(BuildConfig.BASE_URL_API) }
}