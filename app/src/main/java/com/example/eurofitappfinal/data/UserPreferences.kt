package com.example.eurofitappfinal.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val KEY_ID = intPreferencesKey("user_id")
        private val KEY_NAME = stringPreferencesKey("name")
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_PASSWORD = stringPreferencesKey("password")
        private val KEY_WEIGHT = doublePreferencesKey("weight")
        private val KEY_HEIGHT = doublePreferencesKey("height")
        private val KEY_AGE = intPreferencesKey("age")
        private val KEY_GENDER = stringPreferencesKey("gender")
    }

    // ✅ Obtener usuario sin bloquear el hilo principal
    val userFlow: Flow<UserData?> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { preferences ->
            if (preferences.contains(KEY_NAME)) {
                UserData(
                    id = preferences[KEY_ID] ?: 0,
                    name = preferences[KEY_NAME] ?: "",
                    email = preferences[KEY_EMAIL] ?: "",
                    password = preferences[KEY_PASSWORD] ?: "",
                    weight = preferences[KEY_WEIGHT] ?: 0.0,
                    height = preferences[KEY_HEIGHT] ?: 0.0,
                    age = preferences[KEY_AGE] ?: 0,
                    gender = preferences[KEY_GENDER] ?: ""
                )
            } else {
                null
            }
        }

    // ✅ Guardar usuario correctamente en DataStore
    suspend fun saveUser(user: UserData) {
        withContext(Dispatchers.IO) {  // ✅ Se ejecuta en un hilo secundario
            context.dataStore.edit { preferences ->
                preferences[KEY_ID] = user.id
                preferences[KEY_NAME] = user.name
                preferences[KEY_EMAIL] = user.email
                preferences[KEY_PASSWORD] = user.password
                preferences[KEY_WEIGHT] = user.weight
                preferences[KEY_HEIGHT] = user.height
                preferences[KEY_AGE] = user.age
                preferences[KEY_GENDER] = user.gender
            }
        }
    }

    // ✅ Actualizar solo la contraseña
    suspend fun updatePassword(newPassword: String) {
        withContext(Dispatchers.IO) { // ✅ Se ejecuta en un hilo de fondo
            context.dataStore.edit { preferences ->
                preferences[KEY_PASSWORD] = newPassword
            }
        }
    }


    // ✅ Borrar usuario sin bloquear el hilo principal
    suspend fun deleteUser() {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}
