package com.example.eurofitappfinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.eurofitappfinal.ui.screens.*
import com.example.eurofitappfinal.viewmodel.UserViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(Screens.Login.route) {
            LoginScreen(navController, userViewModel)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.UserSettings.route) {
            UserSettingsScreen(
                navController, userViewModel,
                onThemeChange = {}
            )
        }

        composable(Screens.BMI.route) {
            BMIScreen(navController, userViewModel)
        }
        composable(
            route = Screens.TestDetail.route,
            arguments = listOf(navArgument("testName") { type = NavType.StringType })
        ) { backStackEntry ->
            val testName = backStackEntry.arguments?.getString("testName") ?: ""
            TestDetailScreen(navController, testName)
        }

    }
}
