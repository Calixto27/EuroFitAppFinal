package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eurofitappfinal.viewmodel.UserViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BMIScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    val userData by userViewModel.userData.collectAsState(initial = null)

    if (userData == null) {
        Text("Cargando datos del usuario...", style = MaterialTheme.typography.h6)
        return // Evita ejecutar el resto del código si aún no hay datos
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Índice de Masa Corporal (IMC)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Evita que el contenido quede oculto por la AppBar
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            userData?.let { user ->
                val bmi = calculateBMI(user.weight, user.height)

                Text(text = "Peso: ${user.weight} kg", style = MaterialTheme.typography.h6)
                Text(text = "Altura: ${user.height} cm", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "IMC: %.2f".format(bmi), style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = getBMICategory(bmi),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            } ?: run {
                Text("Cargando datos del usuario...", style = MaterialTheme.typography.h6)
            }

        }
    }
}

// Función para calcular el IMC
fun calculateBMI(weight: Double, heightCm: Double): Double {
    val heightM = heightCm / 100
    return weight / (heightM * heightM)
}

// Función para determinar la categoría del IMC
fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Bajo peso"
        bmi in 18.5..24.9 -> "Peso normal"
        bmi in 25.0..29.9 -> "Sobrepeso"
        else -> "Obesidad"
    }
}
