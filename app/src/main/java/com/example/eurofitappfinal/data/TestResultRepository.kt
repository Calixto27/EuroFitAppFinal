package com.example.eurofitappfinal.data

import android.content.Context
import com.example.eurofitappfinal.model.TestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TestResultRepository(context: Context) {
    private val _resultsFlow = MutableStateFlow<List<TestResult>>(emptyList()) // ✅ Ahora usa `TestResult` del modelo
    val resultsFlow: Flow<List<TestResult>> = _resultsFlow.asStateFlow()

    suspend fun saveResults(results: List<TestResult>) {
        _resultsFlow.value = results
    }

    suspend fun deleteAllResults() {
        _resultsFlow.value = emptyList()
    }
}
