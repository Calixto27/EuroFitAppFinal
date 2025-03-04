package com.example.eurofitappfinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eurofitappfinal.ui.screens.*
import com.example.eurofitappfinal.viewmodel.UserViewModel
import com.example.eurofitappfinal.ui.screens.LoginScreen

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
                onThemeChange = TODO()
            )
        }
    }
}
