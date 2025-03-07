package com.valmiraguiar.home.presentation.home

import com.valmiraguiar.core.sharedentity.champion.Champion

sealed interface ChampionListState {
    data object Loading : ChampionListState
    data class Success(val championList: List<Champion>) : ChampionListState
    data class Error(val errorMessage: String) : ChampionListState
}