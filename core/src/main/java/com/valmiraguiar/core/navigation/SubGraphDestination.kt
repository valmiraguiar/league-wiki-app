package com.valmiraguiar.core.navigation

import kotlinx.serialization.Serializable

sealed class SubGraphDestination {
    @Serializable
    data object Home : SubGraphDestination()

    @Serializable
    data object ChampionDetail : SubGraphDestination()
}