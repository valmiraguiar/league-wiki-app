package com.valmiraguiar.detail.data.api

import com.valmiraguiar.core.sharedentity.response.ChampionDetailResponse
import com.valmiraguiar.detail.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailChampionApi {
    @GET("${CURRENT_PATCH}/data/pt_BR/champion/{champion}.json")
    suspend fun getDetailChampion(@Path("champion") champion: String): ChampionDetailResponse

    companion object {
        private const val CURRENT_PATCH = BuildConfig.API_CURRENT_PATCH
    }
}