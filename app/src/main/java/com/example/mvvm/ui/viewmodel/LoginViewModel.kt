package com.example.mvvm.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.repository.AuthRepository
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private val authRepository = AuthRepository()
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading : LiveData<Boolean?> = _isLoading

    private val _isLoginOk = MutableLiveData<Boolean?>()
    val isLoginOk : LiveData<Boolean?> = _isLoginOk

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword : String){
        _password.value = newPassword
    }

    fun onLogin() {
        val emailValue = _email.value ?: ""
        val passwordValue = _password.value ?: ""

        if (!isFormValid(emailValue, passwordValue)) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _isLoginOk.value = false
            try {
                val result = authRepository.login(emailValue, passwordValue)
                result.onSuccess {
                    _isLoginOk.value = true
                }.onFailure { e ->
                    _errorMessage.value = mapFirebaseError(e)
                }
            } catch (e: Exception) {
                _errorMessage.value = mapFirebaseError(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun mapFirebaseError(e: Throwable): String = when (e) {
        is FirebaseAuthInvalidUserException -> "El email no está registrado."
        is FirebaseAuthInvalidCredentialsException -> "Email o contraseña incorrectos."
        is FirebaseTooManyRequestsException -> "Demasiados intentos. Inténtalo más tarde."
        else -> e.message ?: "Error desconocido al iniciar sesión."
    }


    private fun isFormValid(email: String, pass: String): Boolean {
        // Comprobación 1: Campos vacíos
        if (email.isBlank() || pass.isBlank()) {
            _errorMessage.value = "El email y la contraseña no pueden estar vacíos."
            return false
        }

        // Comprobación 2: Formato de email válido
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = "El formato del email no es válido."
            return false
        }

        // Comprobación 3: Longitud de la contraseña (Firebase exige mínimo 6)
        if (pass.length < 6) {
            _errorMessage.value = "La contraseña debe tener al menos 6 caracteres."
            return false
        }

        // Si todas las validaciones pasan
        return true
    }

}