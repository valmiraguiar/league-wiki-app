package com.valmiraguiar.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal class OkHttpBuilder{
    fun build(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(
                okHttpLoggingInterceptor()
            )
            .build()
    }

    private fun okHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
