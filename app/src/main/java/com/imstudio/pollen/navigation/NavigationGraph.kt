package com.imstudio.pollen.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.imstudio.pollen.PollenViewModel
import com.imstudio.pollen.ui.cart.FlowerCartView
import com.imstudio.pollen.ui.flower.FlowerView
import com.imstudio.pollen.ui.flowerList.FlowerListView
import com.imstudio.pollen.ui.payment.FlowerPaymentView

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    pollenViewModel: PollenViewModel,
    context: Context
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.FlowerListView.route
    ) {
        composable(Screens.FlowerListView.route) {
            FlowerListView(
                pollenViewModel = pollenViewModel,
                navController = navController,
                context = context
            )
        }
        composable(Screens.FlowerView.route) {
            FlowerView(
                pollenViewModel = pollenViewModel,
                navController = navController,
            )
        }
        composable(Screens.FlowerCartView.route) {
            FlowerCartView(pollenViewModel = pollenViewModel, navController = navController)
        }
        composable(Screens.FlowerPaymentView.route) {
            FlowerPaymentView(navController = navController, pollenViewModel = pollenViewModel)
        }
    }
}