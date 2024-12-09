package com.valmiraguiar.core.api

import retrofit2.Retrofit

internal class HttpConfigImpl(private val retrofit: Retrofit): HttpConfig {
    override fun getHttpConfig(): Retrofit = retrofit
}