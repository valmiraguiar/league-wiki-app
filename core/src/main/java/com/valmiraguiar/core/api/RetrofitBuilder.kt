package com.valmiraguiar.core.api

import com.google.gson.GsonBuilder
import com.valmiraguiar.core.repository.BaseWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitBuilder(private val baseWebService: BaseWebService) {
    fun build(client: OkHttpClient): Retrofit {
        fun gsonProvider() = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(baseWebService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonProvider()))
            .client(client)
            .build()
    }
}