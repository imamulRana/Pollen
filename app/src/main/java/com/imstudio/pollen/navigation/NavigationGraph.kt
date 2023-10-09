package com.imstudio.pollen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.ui.cart.FlowerCartView
import com.imstudio.pollen.ui.flower.FlowerView
import com.imstudio.pollen.ui.flowerList.FlowerListView

@Composable
fun NavigationGraph(
    navController: NavHostController, pollenViewModel: PollenViewModel
) {
    NavHost(navController = navController, startDestination = Screens.FlowerListView.route) {
        composable(Screens.FlowerListView.route) {
            FlowerListView(pollenViewModel = pollenViewModel, navController = navController)
        }
        composable(Screens.FlowerView.route) {
            FlowerView(pollenViewModel = pollenViewModel, navController = navController)
        }
        composable(Screens.FlowerCartView.route) {
            FlowerCartView(pollenViewModel = pollenViewModel)
        }
    }
}