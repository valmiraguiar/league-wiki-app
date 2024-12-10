package com.valmiraguiar.detail.data.repository

import com.azul.domain.repository.DetailChampionRepository
import com.valmiraguiar.core.infrastructure.SafeApiCaller
import com.valmiraguiar.core.infrastructure.onError
import com.valmiraguiar.core.infrastructure.onSuccess
import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import com.valmiraguiar.detail.data.api.DetailChampionApi
import com.valmiraguiar.detail.data.mapper.ChampionDetailMapperImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.io.IOException

class DetailChampionRepositoryImpl(
    private val api: DetailChampionApi,
    private val safeApiCaller: SafeApiCaller
) : DetailChampionRepository {
    override suspend fun getDetailChampion(champion: String): Flow<ChampionDetail> {
        return flow {
            emit(safeApiCaller.safeApiCall { api.getDetailChampion(champion = champion) })
        }.transform { result ->
            result.run {
                onSuccess { scope ->
                    emit(ChampionDetailMapperImpl().convert(scope))
                }

                onError {
                    throw it.exception ?: IOException()
                }
            }
        }
    }
}