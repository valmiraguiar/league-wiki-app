package com.valmiraguiar.home.data.business

import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.domain.business.ChampionBusiness
import com.valmiraguiar.domain.repository.ChampionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChampionsBusinessImpl(
    private val repository: ChampionsRepository
) : ChampionBusiness {
    override suspend fun getChampionList(): Flow<List<Champion>> {
        return flow {
            repository.getChampionList().collect { response ->
                emit(response)
            }
        }
    }
}