package com.example.examen5.network
import retrofit2.http.GET

// Interfaz que define las operaciones de la API para Retrofit
interface CurrencyApiService {

    // Petición GET al endpoint que nos da las tasas más recientes basadas en USD
    // Usamos 'suspend' porque esta llamada se hará en una corrutina (hilo secundario)
    @GET("latest/USD")
    suspend fun getExchangeRates(): CurrencyResponse
}