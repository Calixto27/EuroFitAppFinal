package com.example.eurofitappfinal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.eurofitappfinal.data.UserPreferences
import com.example.eurofitappfinal.data.UserData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    // ✅ Flow que recibe datos de DataStore
    val userData: StateFlow<UserData?> = userPreferences.userFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    // ✅ Guardar datos en DataStore
    fun saveUser(user: UserData) {
        viewModelScope.launch {
            Log.d("UserViewModel", "Guardando usuario en ViewModel: $user") // Debug
            userPreferences.saveUser(user)
        }
    }



    fun updatePassword(newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferences.updatePassword(newPassword)
        }
    }
}
