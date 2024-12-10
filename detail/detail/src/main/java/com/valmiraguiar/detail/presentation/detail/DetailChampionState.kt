package com.valmiraguiar.detail.presentation.detail

import com.valmiraguiar.core.sharedentity.champion.ChampionDetail

sealed interface DetailChampionState {
    data object Loading : DetailChampionState
    data class Success(val championDetail: ChampionDetail) : DetailChampionState
    data class Error(val errorMessage: String) : DetailChampionState
}