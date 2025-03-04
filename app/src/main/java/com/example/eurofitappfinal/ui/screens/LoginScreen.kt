package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eurofitappfinal.navigation.Screens
import com.example.eurofitappfinal.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    // ✅ Obtener la contraseña almacenada desde DataStore
    val userData by userViewModel.userData.collectAsState()
    var storedPassword by remember { mutableStateOf("1234") } // Contraseña por defecto

    // ✅ Se actualiza `storedPassword` cuando cambia `userData`
    LaunchedEffect(userData) {
        storedPassword = userData?.password ?: "1234"
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Inicio de Sesión") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ingrese su contraseña")

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") }
            )

            Button(
                onClick = {
                    if (password == storedPassword) {
                        navController.navigate(Screens.Home.route)
                    } else {
                        errorMessage = "Contraseña incorrecta"
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Ingresar")
            }

            Button(
                onClick = { showChangePasswordDialog = true },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Cambiar Contraseña")
            }

            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = MaterialTheme.colors.error)
            }
        }
    }

    if (showChangePasswordDialog) {
        ChangePasswordDialog(
            onDismiss = { showChangePasswordDialog = false },
            onChangePassword = { oldPassword, newPassword ->
                if (oldPassword == storedPassword) {
                    coroutineScope.launch {
                        userViewModel.updatePassword(newPassword)
                        showChangePasswordDialog = false
                        errorMessage = "Contraseña actualizada"
                    }
                } else {
                    errorMessage = "Contraseña actual incorrecta"
                }
            }
        )
    }
}

@Composable
fun ChangePasswordDialog(onDismiss: () -> Unit, onChangePassword: (String, String) -> Unit) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cambiar Contraseña") },
        text = {
            Column {
                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = { oldPassword = it },
                    label = { Text("Contraseña Actual") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Nueva Contraseña") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onChangePassword(oldPassword, newPassword) }) {
                Text("Cambiar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
