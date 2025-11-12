package com.example.mvvm.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel(){

    private val authRepository = AuthRepository()
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading : LiveData<Boolean?> = _isLoading

    private val _isRegisterOk = MutableLiveData<Boolean?>()
    val isRegisterOk : LiveData<Boolean?> = _isRegisterOk

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword : String){
        _password.value = newPassword
    }

    fun onRegister(){
        val emailValue: String
        if (_email.value != null) {
            emailValue = _email.value
        } else {
            emailValue = ""
        }

        val passwordValue: String
        if (_email.value != null) {
            passwordValue = _password.value
        } else {
            passwordValue = ""
        }

        if(emailValue.isBlank() || passwordValue.isBlank()){
            //poner todas las comprobaciones
            if (!isFormValid(emailValue, passwordValue)) {
                return // Si el formulario no es válido, la función termina aquí.
            }

            _errorMessage.value = "Error al insertar datos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            val result = authRepository.register(emailValue, passwordValue)
            result.onSuccess {
                _isRegisterOk.value = true
            }.onFailure { e ->
                _errorMessage.value = e.message
            }

            _isLoading.value = false
        }
    }

    private fun isFormValid(email: String, pass: String): Boolean {
        // Comprobación 1: Campos vacíos
        if (email.isBlank() || pass.isBlank()) {
            _errorMessage.value = "El email y la contraseña no pueden estar vacíos."
            return false
        }

        // Comprobación 2: Formato de email correcto
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = "El formato del email no es válido."
            return false
        }

        // Comprobación 3: Longitud de la contraseña
        if (pass.length < 6) {
            _errorMessage.value = "La contraseña debe tener al menos 6 caracteres."
            return false
        }

        // Comprobación 4: Contraseña contiene al menos una letra y un número
        val hasLetter = pass.any { it.isLetter() }
        val hasDigit = pass.any { it.isDigit() }
        if (!hasLetter || !hasDigit) {
            _errorMessage.value = "La contraseña debe contener al menos una letra y un número."
            return false
        }

        // Si todas las comprobaciones son correctas
        return true
    }
}