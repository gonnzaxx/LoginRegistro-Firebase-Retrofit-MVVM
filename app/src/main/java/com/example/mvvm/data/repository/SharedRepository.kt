package com.example.mvvm.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedRepository(context: Context) {

    // Acceso a SharedPreferences
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Guarda email y contraseña del login
    fun saveLogin(email: String, password: String) {
        prefs.edit {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
        }
    }

    // Obtiene el email guardado
    fun getEmail(): String = prefs.getString(KEY_EMAIL, "") ?: ""
    // Obtiene la contraseña guardado
    fun getPassword(): String = prefs.getString(KEY_PASSWORD, "") ?: ""

    // Guarda nombre y teléfono del perfil
    fun saveProfile(name: String, phone: String) {
        prefs.edit {
            putString(KEY_NAME, name)
            putString(KEY_PHONE, phone)
        }
    }

    fun getName(): String = prefs.getString(KEY_NAME, "") ?: ""
    fun getPhone(): String = prefs.getString(KEY_PHONE, "") ?: ""

    // Elimina todos los datos guardados
    fun clearAll() {
        prefs.edit { clear() }
    }

    // Constantes con nombres de claves
    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_EMAIL = "key_email"
        private const val KEY_PASSWORD = "key_password"
        private const val KEY_NAME = "key_name"
        private const val KEY_PHONE = "key_phone"
    }
}