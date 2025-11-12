package com.example.mvvm.data.repository

import androidx.compose.material3.Text
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val firebaseAuth: FirebaseAuth  = FirebaseAuth.getInstance()

    fun getCurrentUser() : FirebaseUser?{
        return firebaseAuth.currentUser
    }
    fun logOut(){
        firebaseAuth.signOut()
    }

    fun isUserLogin() : Boolean{
        return firebaseAuth.currentUser != null
    }

    suspend fun register(email: String, pass: String): Result<FirebaseUser> = try {
        val res = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al registrar usuario"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun login(email: String, pass: String): Result<FirebaseUser> = try {
        val res = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al iniciar sesión"))
    } catch (e: Exception) {
        Result.failure(e)
    }


}