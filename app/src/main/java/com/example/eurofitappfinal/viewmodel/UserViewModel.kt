package com.example.eurofitappfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.eurofitappfinal.data.UserPreferences
import com.example.eurofitappfinal.data.UserData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    // ✅ Convertimos el Flow en un StateFlow para evitar bloqueos
    val userData: StateFlow<UserData?> = userPreferences.userFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    // ✅ Guardar los datos del usuario en `Dispatchers.IO`
    fun saveUser(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferences.saveUser(user)
        }
    }

    // ✅ Actualizar la contraseña en `Dispatchers.IO`
    fun updatePassword(newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferences.updatePassword(newPassword)
        }
    }
}
