package com.example.eurofitappfinal.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val KEY_NAME = stringPreferencesKey("name")
        val KEY_EMAIL = stringPreferencesKey("email")
        val KEY_PASSWORD = stringPreferencesKey("password")
        val KEY_AGE = intPreferencesKey("age")
        val KEY_HEIGHT = doublePreferencesKey("height")
        val KEY_WEIGHT = doublePreferencesKey("weight")
        val KEY_GENDER = stringPreferencesKey("gender")
    }

    // ✅ Obtener datos del usuario almacenados
    val userFlow: Flow<UserData> = dataStore.data.map { preferences ->
        UserData(
            name = preferences[KEY_NAME] ?: "",
            email = preferences[KEY_EMAIL] ?: "",
            password = preferences[KEY_PASSWORD] ?: "1234",
            age = preferences[KEY_AGE] ?: 18,
            height = preferences[KEY_HEIGHT] ?: 170.0,
            weight = preferences[KEY_WEIGHT] ?: 70.0,
            gender = preferences[KEY_GENDER] ?: "Otro"
        )
    }

    // ✅ Guardar datos del usuario
    suspend fun saveUser(user: UserData) {
        dataStore.edit { preferences ->
            preferences[KEY_NAME] = user.name
            preferences[KEY_EMAIL] = user.email
            preferences[KEY_PASSWORD] = user.password
            preferences[KEY_AGE] = user.age
            preferences[KEY_HEIGHT] = user.height
            preferences[KEY_WEIGHT] = user.weight
            preferences[KEY_GENDER] = user.gender
        }
    }

    // ✅ Actualizar solo la contraseña
    suspend fun updatePassword(newPassword: String) {
        dataStore.edit { preferences ->
            preferences[KEY_PASSWORD] = newPassword
        }
    }

    // ✅ Eliminar los datos del usuario (RESET)
    suspend fun deleteUser() {
        dataStore.edit { preferences ->
            preferences.clear() // 🔥 Esto borra todos los datos almacenados en DataStore
        }
    }
}
