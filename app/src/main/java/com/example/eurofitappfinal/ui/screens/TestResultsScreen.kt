package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eurofitappfinal.R
import com.example.eurofitappfinal.viewmodel.TestResultViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestResultsScreen(
    navController: NavController,
    testResultViewModel: TestResultViewModel = viewModel()
) {
    val context = LocalContext.current
    val results by testResultViewModel.testResults.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resultados Guardados") },
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
                painter = painterResource(id = R.drawable.fondo2),
                contentDescription = "Fondo Resultados",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Historial de pruebas:", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(16.dp))

                if (results.isEmpty()) {
                    Text("No hay resultados guardados")
                } else {
                    results.forEach { result ->
                        Text("${result.testName}: ${result.score}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    coroutineScope.launch {
                        shareResults(context, results.joinToString("\n") { "${it.testName}: ${it.score}" })
                    }
                }) {
                    Text("Exportar Resultados")
                }
            }
        }
    }
}

fun shareResults(context: android.content.Context, results: String) {
    if (results.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Resultados de Pruebas Físicas")
            putExtra(Intent.EXTRA_TEXT, results)
        }
        context.startActivity(Intent.createChooser(intent, "Compartir Resultados"))
    }
}
