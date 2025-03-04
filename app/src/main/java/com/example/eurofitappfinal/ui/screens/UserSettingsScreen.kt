package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.eurofitappfinal.R
import com.example.eurofitappfinal.data.UserData
import com.example.eurofitappfinal.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserSettingsScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    onThemeChange: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val userDataState by userViewModel.userData.collectAsState(initial = null)

    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    //Se actualizan los campos con los valores actuales del usuario
    LaunchedEffect(userDataState) {
        userDataState?.let { user ->
            age = user.age.toString()
            height = user.height.toString()
            weight = user.weight.toString()
            gender = user.gender
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = "Fondo Configuración",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Edad", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary)
                OutlinedTextField(value = age, onValueChange = { age = it })

                Text("Altura (cm)", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary)
                OutlinedTextField(value = height, onValueChange = { height = it })

                Text("Peso (kg)", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary)
                OutlinedTextField(value = weight, onValueChange = { weight = it })

                Text("Género", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onPrimary)
                OutlinedTextField(value = gender, onValueChange = { gender = it })

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    coroutineScope.launch {
                        userDataState?.let { currentUser ->
                            val updatedUser = currentUser.copy(
                                age = age.toIntOrNull() ?: currentUser.age,
                                height = height.toDoubleOrNull() ?: currentUser.height,
                                weight = weight.toDoubleOrNull() ?: currentUser.weight,
                                gender = gender
                            )
                            println("Guardando datos: $updatedUser") // 🔍 Verifica los datos antes de guardar
                            userViewModel.saveUser(updatedUser)
                        }
                    }
                }) {
                    Text("Guardar")
                }
            }
        }
    }
}
