package com.valmiraguiar.home.data.repository

import com.valmiraguiar.core.infrastructure.SafeApiCaller
import com.valmiraguiar.core.infrastructure.onError
import com.valmiraguiar.core.infrastructure.onSuccess
import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.domain.repository.ChampionsRepository
import com.valmiraguiar.home.data.api.HomeApi
import com.valmiraguiar.home.data.mapper.ChampionsMapperImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.io.IOException

class ChampionsRepositoryImpl(
    private val api: HomeApi,
    private val safeApiCaller: SafeApiCaller,
) : ChampionsRepository {
    override suspend fun getChampionList(): Flow<List<Champion>> {
        return flow {
            emit(safeApiCaller.safeApiCall { api.getChampionList() })
        }.transform { result ->
            result.run {
                onSuccess { scope ->
                    emit(ChampionsMapperImpl().convert(scope))
                }

                onError {
                    throw it.exception ?: IOException()
                }
            }
        }
    }
}