package com.imstudio.pollen

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.imstudio.pollen.ui.theme.PollenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val pollenViewModel: PollenViewModel = viewModel()
            val navController = rememberNavController()
            val context = LocalContext.current
            PollenTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
//                    FlowerListView(pollenViewModel = pollenViewModel, navController = navController)
                    PollenApp(
                        navController = navController,
                        pollenViewModel = pollenViewModel,
                        context = context
                    )
                }
            }
        }
    }
}