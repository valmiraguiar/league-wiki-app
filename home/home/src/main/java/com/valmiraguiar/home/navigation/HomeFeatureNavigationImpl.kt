package com.valmiraguiar.home.navigation

import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.valmiraguiar.core.navigation.Destination
import com.valmiraguiar.core.navigation.SubGraphDestination
import com.valmiraguiar.core.navigation.features.HomeFeatureNavigation
import com.valmiraguiar.home.presentation.home.HomeScreen
import com.valmiraguiar.home.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class HomeFeatureNavigationImpl : HomeFeatureNavigation {
    override fun registerGraph(
        navHostController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.Home>(startDestination = Destination.Home) {
            composable<Destination.Home> {
                val vm = koinViewModel<HomeViewModel>()

                HomeScreen(viewModel = vm) {
                    navHostController.navigate(Destination.ChampionDetail)
                }
            }
        }
    }
}