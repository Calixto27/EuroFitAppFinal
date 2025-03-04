package com.example.eurofitappfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.eurofitappfinal.navigation.Navigation
import com.example.eurofitappfinal.ui.theme.EuroFitAppFinalTheme
import com.example.eurofitappfinal.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuroFitAppFinalTheme {
                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()

                // 🔹 Configuramos la navegación
                Navigation(
                    navController = navController,
                    userViewModel = userViewModel
                )
            }
        }
    }
}
