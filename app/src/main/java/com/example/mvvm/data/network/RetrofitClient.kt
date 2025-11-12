package com.example.mvvm.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    // URL base de la API
    private const val BASE_URL = "https://swapi.dev/api/"

    // Configura Retrofit con la URL base y el convertidor JSON
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    // Crea una instancia del servicio definido en la interfaz SWAPI
    val swAPI : SWAPI = retrofit.create(SWAPI::class.java)
}