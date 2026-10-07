package com.example.responsimobilef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.responsimobilef.ui.screen.DetailScreen
import com.example.responsimobilef.ui.screen.HomeScreen
import com.example.responsimobilef.ui.theme.ResponsiMobileFTheme
import com.example.responsimobilef.ui.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResponsiMobileFTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val gameViewModel: GameViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController = navController, viewModel = gameViewModel)
                        }
                        composable(
                            route = "detail/{gameId}",
                            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
                            DetailScreen(gameId = gameId, navController = navController, viewModel = gameViewModel)
                        }
                    }
                }
            }
        }
    }
}