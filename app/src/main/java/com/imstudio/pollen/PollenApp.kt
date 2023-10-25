package com.imstudio.pollen

import android.content.Context
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.imstudio.pollen.navigation.NavigationGraph
import com.imstudio.pollen.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollenApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    pollenViewModel: PollenViewModel,
    context: Context
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentTitle = when (currentDestination?.destination?.route) {
        Screens.FlowerListView.route -> Screens.FlowerListView.title
        Screens.FlowerView.route -> Screens.FlowerView.title
        Screens.FlowerCartView.route -> Screens.FlowerCartView.title
        Screens.FlowerPaymentView.route -> Screens.FlowerPaymentView.title
        else -> ""
    }
    val canNavigateBack = navController.previousBackStackEntry
    Scaffold(contentWindowInsets = WindowInsets.statusBars, topBar = {
        TopAppBar(
            navigationIcon = {
                if (canNavigateBack != null) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            },
            title = {
                Text(
                    text = currentTitle,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                scrolledContainerColor = MaterialTheme.colorScheme.surface
            ),
            actions = {
                if (currentDestination?.destination?.route != Screens.FlowerCartView.route) IconButton(
                    onClick = { navController.navigate(Screens.FlowerCartView.route) }) {
                    Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "")
                }
            }
        )
    }) {
        NavigationGraph(
            modifier = modifier
                .padding(it),
            navController = navController,
            pollenViewModel = pollenViewModel,
            context = context
        )

    }
}