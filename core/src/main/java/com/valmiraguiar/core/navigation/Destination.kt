package com.valmiraguiar.core.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data object Home : Destination()

    @Serializable
    data class ChampionDetail(val championId: String) : Destination()
}