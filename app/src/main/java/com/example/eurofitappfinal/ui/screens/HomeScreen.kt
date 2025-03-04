package com.example.eurofitappfinal.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.eurofitappfinal.data.TestRepository
import com.example.eurofitappfinal.model.TestModel
import com.example.eurofitappfinal.navigation.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val testList = remember { TestRepository.testList } // 🔹 Guardamos la lista en un `remember`
    val filteredTests = remember(searchQuery) {
        testList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pruebas Físicas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.BMI.route) }) {
                        Icon(Icons.Default.Favorite, contentDescription = "IMC")
                    }
                    IconButton(onClick = { navController.navigate(Screens.UserSettings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                    IconButton(onClick = { navController.navigate(Screens.TestResults.route) }) {
                        Icon(Icons.Default.List, contentDescription = "Resultados")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar prueba") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredTests.isEmpty()) {
                Text("No se encontraron pruebas", style = MaterialTheme.typography.body1)
            } else {
                LazyColumn {
                    items(filteredTests) { test ->
                        TestItem(test, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TestItem(test: TestModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(Screens.TestDetail.createRoute(test.name)) },
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = test.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = test.name, style = MaterialTheme.typography.h6)
        }
    }
}
