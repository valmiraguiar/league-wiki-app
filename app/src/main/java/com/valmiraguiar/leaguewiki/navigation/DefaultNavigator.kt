package com.valmiraguiar.leaguewiki.navigation

import com.valmiraguiar.detail.navigation.ChampionDetailFeatureNavigation
import com.valmiraguiar.home.navigation.HomeFeatureNavigation

data class DefaultNavigator(
    val homeFeature: HomeFeatureNavigation,
    val championDetailFeature: ChampionDetailFeatureNavigation
)