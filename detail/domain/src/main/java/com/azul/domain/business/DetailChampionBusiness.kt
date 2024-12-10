package com.azul.domain.business

import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import kotlinx.coroutines.flow.Flow

interface DetailChampionBusiness {
    suspend fun getDetailChampion(champion: String): Flow<ChampionDetail>
}