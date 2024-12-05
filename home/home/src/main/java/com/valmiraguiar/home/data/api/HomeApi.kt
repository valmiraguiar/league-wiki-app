package com.valmiraguiar.home.data.api

import com.valmiraguiar.core.sharedentity.response.ChampionListResponse
import retrofit2.http.GET

interface HomeApi {
    @GET("${CURRENT_PATCH}/data/pt_BR/champion.json")
    suspend fun getChampionList(): ChampionListResponse

    companion object {
        private const val CURRENT_PATCH = "2.24.24"
    }
}