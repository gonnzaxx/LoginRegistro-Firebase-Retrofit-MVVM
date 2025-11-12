package com.example.mvvm.ui.viewmodel

// DatosViewModelFactory.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.repository.SharedRepository

class DatosViewModelFactory(
    private val sharedRepo: SharedRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatosViewModel::class.java)) {
            return DatosViewModel(sharedRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
