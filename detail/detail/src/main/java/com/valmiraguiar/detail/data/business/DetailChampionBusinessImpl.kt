package com.valmiraguiar.detail.data.business

import com.azul.domain.business.DetailChampionBusiness
import com.azul.domain.repository.DetailChampionRepository
import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailChampionBusinessImpl(
    private val repository: DetailChampionRepository
) : DetailChampionBusiness {
    override suspend fun getDetailChampion(champion: String): Flow<ChampionDetail> {
        return flow {
            repository.getDetailChampion(champion).collect { response ->
                emit(response)
            }
        }
    }
}