package com.example.eurofitappfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.eurofitappfinal.data.TestResultRepository
import com.example.eurofitappfinal.model.TestResult // ✅ Asegúrate de importar desde `model/`
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TestResultViewModel(application: Application) : AndroidViewModel(application) {
    private val testResultRepository = TestResultRepository(application)

    private val _testResults = MutableStateFlow<List<TestResult>>(emptyList())
    val testResults: StateFlow<List<TestResult>> = _testResults.asStateFlow()

    init {
        loadResults()
    }

    private fun loadResults() {
        viewModelScope.launch {
            testResultRepository.resultsFlow.collect { results ->
                _testResults.value = results
            }
        }
    }

    fun saveResults(results: List<TestResult>) {
        viewModelScope.launch {
            testResultRepository.saveResults(results)
            loadResults()
        }
    }

    fun deleteAllResults() {
        viewModelScope.launch {
            testResultRepository.deleteAllResults()
            _testResults.value = emptyList()
        }
    }
}
