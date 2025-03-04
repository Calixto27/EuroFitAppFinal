package com.example.eurofitappfinal.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreferences: UserPreferences) {

    val userData: Flow<UserData?> = userPreferences.userFlow

    // ✅ Guardar usuario con todos los datos
    suspend fun saveUser(user: UserData) {
        userPreferences.saveUser(user)
    }

    // ✅ Actualizar solo la contraseña
    suspend fun updatePassword(newPassword: String) {
        userPreferences.updatePassword(newPassword)
    }

    // ✅ Eliminar usuario
    suspend fun deleteUser() {
        userPreferences.deleteUser()
    }
}
