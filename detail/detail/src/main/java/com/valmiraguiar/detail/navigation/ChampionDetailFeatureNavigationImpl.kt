package com.valmiraguiar.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.valmiraguiar.core.navigation.Destination
import com.valmiraguiar.core.navigation.SubGraphDestination
import com.valmiraguiar.core.navigation.features.ChampionDetailFeatureNavigation
import com.valmiraguiar.detail.presentation.detail.ChampionDetailScreen

class ChampionDetailFeatureNavigationImpl : ChampionDetailFeatureNavigation {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.ChampionDetail>(
            startDestination = Destination.ChampionDetail(EMPTY_VALUE)
        ) {
            composable<Destination.ChampionDetail> {
                val championId = it.toRoute<Destination.ChampionDetail>().championId

                ChampionDetailScreen(championId = championId) {
                    navHostController.navigateUp()
                }
            }
        }
    }

    companion object {
        private const val EMPTY_VALUE = ""
    }
}