package com.valmiraguiar.domain.repository

import com.valmiraguiar.core.sharedentity.champion.Champion
import kotlinx.coroutines.flow.Flow

interface ChampionsRepository {
    suspend fun getChampionList(): Flow<List<Champion>>
}