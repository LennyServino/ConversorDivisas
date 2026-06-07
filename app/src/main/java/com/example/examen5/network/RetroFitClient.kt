package com.example.examen5.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private const val BASE_URL = "https://open.er-api.com/v6/"

    // Inicialización perezosa (lazy) de nuestro servicio
    // Solo se crea la primera vez que se manda a llamar para ahorrar recursos
    val apiService: CurrencyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Agregamos el convertidor de Gson que configuramos en build.gradle
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }
}