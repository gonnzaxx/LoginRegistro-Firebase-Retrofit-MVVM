package com.example.mvvm.ui.viewmodel

import androidx.lifecycle.*
import com.example.mvvm.data.models.Jedai
import com.example.mvvm.data.repository.JedaiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VistaViewModel : ViewModel(){
    private val jedaiRepository = JedaiRepository()

    // Lista observable de Jedais
    private val _jedaiList = MutableLiveData<List<Jedai>>(emptyList())
    val jedaiList : LiveData<List<Jedai>> = _jedaiList

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean> = _isLoading

    // índice actual que se mostrará en pantalla
    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex


    // Carga la lista de Jedais desde el repositorio
    fun loadJedaiList(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = withContext(Dispatchers.IO) { jedaiRepository.getJedaiList() }
            if(result == null){
                _errorMessage.value = "Error al obtener datos"
                _jedaiList.value = emptyList()
                _currentIndex.value = 0
            }else{
                _errorMessage.value = null
                _jedaiList.value = result
                // reiniciamos el índice por si venimos de otro estado
                _currentIndex.value = 0
            }
            _isLoading.value = false
        }
    }

    // Muestra el siguiente Jedai de la lista
    fun nextJedai(){
        val list = _jedaiList.value ?: emptyList()
        if(list.isNotEmpty()){
            val next = ((_currentIndex.value ?: 0) + 1) % list.size
            _currentIndex.value = next
        }
    }
}
