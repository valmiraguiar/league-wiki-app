package com.valmiraguiar.leaguewiki.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.valmiraguiar.core.navigation.SubGraphDestination

@Composable
fun MainNavigation(modifier: Modifier = Modifier, defaultNavigator: DefaultNavigator) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SubGraphDestination.Home) {
        defaultNavigator.homeFeature.registerGraph(navController, this)
        defaultNavigator.championDetailFeature.registerGraph(navController, this)
    }
}