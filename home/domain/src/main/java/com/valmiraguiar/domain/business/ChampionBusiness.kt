package com.valmiraguiar.domain.business

import com.valmiraguiar.core.sharedentity.champion.Champion
import kotlinx.coroutines.flow.Flow

interface ChampionBusiness {
    suspend fun getChampionList(): Flow<List<Champion>>
}