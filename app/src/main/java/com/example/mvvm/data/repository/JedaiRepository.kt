package com.example.mvvm.data.repository

import com.example.mvvm.data.models.Jedai
import com.example.mvvm.data.network.RetrofitClient


class JedaiRepository {
    // Instancia de la API de Star Wars
    private val api = RetrofitClient.swAPI

    // Obtiene la lista de Jedais desde la API
    suspend fun getJedaiList() : ArrayList<Jedai>?{
        val call = api.getJedais().execute()

        val body = call.body()
        if(call.isSuccessful){
            return body?.results //devuelvo el array de jedais
        }else{
            return ArrayList<Jedai>() //devuelvo un array vacío
        }
    }
}