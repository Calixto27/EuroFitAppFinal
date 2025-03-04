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
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ingrese su resultado para $testName")
            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                label = { Text("Ingrese su marca") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val value = userValue.toDoubleOrNull()
                if (value != null) {
                    val userId = testResults.firstOrNull()?.userId ?: 12
                    score = ScoringRepository.getScore(testName, userId, "Masculino", value)
                }
            }) {
                Text("Calcular Nota")
            }

            Spacer(modifier = Modifier.height(16.dp))

            score?.let {
                Text("Su nota es: $it", style = MaterialTheme.typography.h6)
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
