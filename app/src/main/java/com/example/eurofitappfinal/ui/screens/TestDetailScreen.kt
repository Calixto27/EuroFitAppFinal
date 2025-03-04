package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eurofitappfinal.R
import com.example.eurofitappfinal.data.ScoringRepository
import com.example.eurofitappfinal.model.TestResult
import com.example.eurofitappfinal.viewmodel.TestResultViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestDetailScreen(
    navController: NavController,
    testName: String,
    testResultViewModel: TestResultViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val testResults by testResultViewModel.testResults.collectAsState(initial = emptyList())

    var userValue by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Double?>(null) }
    var selectedGender by remember { mutableStateOf("Masculino") }
    val genderOptions = listOf("Masculino", "Femenino")

    val userAge = testResults.firstOrNull()?.userId ?: 12

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(testName) },
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
                contentDescription = "Fondo Test",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ingrese su resultado para $testName", style = MaterialTheme.typography.h6)

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Selector de género
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(onClick = { expanded = true }) {
                        Text(selectedGender)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        genderOptions.forEach { gender ->
                            DropdownMenuItem(onClick = {
                                selectedGender = gender
                                expanded = false
                            }) {
                                Text(gender)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Campo para ingresar el resultado
                OutlinedTextField(
                    value = userValue,
                    onValueChange = { userValue = it },
                    label = { Text("Ingrese su marca") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Botón para calcular la nota
                Button(onClick = {
                    val value = userValue.toDoubleOrNull()
                    if (value != null) {
                        score = ScoringRepository.getScore(testName, userAge, selectedGender, value)
                    }
                }) {
                    Text("Calcular Nota")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Mostrar la nota obtenida
                score?.let {
                    Text("Su nota es: $it", style = MaterialTheme.typography.h5)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 🔹 Botón para guardar el resultado
                    Button(onClick = {
                        coroutineScope.launch {
                            testResultViewModel.saveResults(
                                listOf(TestResult(0, testName, it.toFloat()))
                            )
                        }
                    }) {
                        Text("Guardar Resultado")
                    }
                }
            }
        }
    }
}
