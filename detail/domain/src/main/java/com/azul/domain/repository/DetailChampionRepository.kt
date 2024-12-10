package com.azul.domain.repository

import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import kotlinx.coroutines.flow.Flow

interface DetailChampionRepository {
    suspend fun getDetailChampion(champion: String): Flow<ChampionDetail>
}