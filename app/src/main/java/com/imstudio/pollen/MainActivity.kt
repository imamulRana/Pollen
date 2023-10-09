package com.imstudio.pollen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.imstudio.pollen.navigation.NavigationGraph
import com.imstudio.pollen.ui.theme.PollenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pollenViewModel: PollenViewModel = viewModel()
            val navController = rememberNavController()

            PollenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        NavigationGraph(
                            navController = navController,
                            pollenViewModel = pollenViewModel
                        )
                    }
                }
            }
        }
    }
}

