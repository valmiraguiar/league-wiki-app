package com.valmiraguiar.core.api

import retrofit2.Retrofit

interface HttpConfig {
    fun getHttpConfig(): Retrofit
}

inline fun <reified T : Any> HttpConfig.create(): T = getHttpConfig().create(T::class.java)