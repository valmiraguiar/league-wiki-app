package com.valmiraguiar.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureNavigation {
    fun registerGraph(navHostController: NavHostController, navGraphBuilder: NavGraphBuilder)
}