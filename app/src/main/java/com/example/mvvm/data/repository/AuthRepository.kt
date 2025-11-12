package com.example.mvvm.data.repository

import androidx.compose.material3.Text
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    // Instancia de Firebase Authentication
    private val firebaseAuth: FirebaseAuth  = FirebaseAuth.getInstance()

    // Devuelve el usuario actualmente autenticado (si lo hay)
    fun getCurrentUser() : FirebaseUser?{
        return firebaseAuth.currentUser
    }

    // Cierra la sesión del usuario actual
    fun logOut(){
        firebaseAuth.signOut()
    }

    // Comprueba si hay un usuario con sesión iniciada
    fun isUserLogin() : Boolean{
        return firebaseAuth.currentUser != null
    }

    // Registra un nuevo usuario con email y contraseña
    suspend fun register(email: String, pass: String): Result<FirebaseUser> = try {
        val res = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al registrar usuario"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    // Inicia sesión con email y contraseña
    suspend fun login(email: String, pass: String): Result<FirebaseUser> = try {
        val res = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al iniciar sesión"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}