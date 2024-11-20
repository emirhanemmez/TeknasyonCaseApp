package com.emirhanemmez.teknasyoncase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emirhanemmez.common.presentation.ui.theme.TeknasyonCaseTheme
import com.emirhanemmez.feature.detail.presentation.DetailScreen
import com.emirhanemmez.feature.list.presentation.ListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeknasyonCaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val navHostController = rememberNavController()
                        Navigation(navHostController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ListScreen(
                navigateToDetail = { id, name ->
                    navController.navigate("detail/$id/$name")
                }
            )
        }

        composable(
            route = "detail/{id}/{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val name = backStackEntry.arguments?.getString("name")

            if (id != null && name != null) {
                DetailScreen(id, name)
            }
        }
    }
}
