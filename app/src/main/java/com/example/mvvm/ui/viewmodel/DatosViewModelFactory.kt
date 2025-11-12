package com.example.mvvm.ui.viewmodel

// DatosViewModelFactory.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.repository.SharedRepository

class DatosViewModelFactory(
    private val sharedRepo: SharedRepository
) : ViewModelProvider.Factory {

    // Crea instancias del ViewModel de forma controlada
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Comprueba si el ViewModel solicitado es DatosViewModel
        if (modelClass.isAssignableFrom(DatosViewModel::class.java)) {
            // Devuelve una nueva instancia pasando el repositorio
            return DatosViewModel(sharedRepo) as T
        }
        // Lanza excepción si el tipo no coincide
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
