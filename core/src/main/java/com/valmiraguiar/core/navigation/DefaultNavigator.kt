package com.valmiraguiar.core.navigation

import com.valmiraguiar.core.navigation.features.ChampionDetailFeatureNavigation
import com.valmiraguiar.core.navigation.features.HomeFeatureNavigation

data class DefaultNavigator(
    val homeFeature: HomeFeatureNavigation,
    val championDetailFeature: ChampionDetailFeatureNavigation
)