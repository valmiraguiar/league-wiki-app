package com.valmiraguiar.core.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.valmiraguiar.core.repository.BaseWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitBuilder(private val baseWebService: BaseWebService) {
    fun build(client: OkHttpClient): Retrofit {
        fun gsonProvider() = GsonBuilder().create()
        return createService(baseWebService.baseUrl, client, gsonProvider())
    }

    private inline fun <reified T> createService(
        baseUrl: String,
        client: OkHttpClient,
        gson: Gson
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(T::class.java)
    }
}