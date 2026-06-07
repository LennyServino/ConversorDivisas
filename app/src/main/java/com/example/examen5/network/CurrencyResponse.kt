package com.example.examen5.network

// Clase de datos que mapea la respuesta de la API
data class CurrencyResponse (
    val result: String,
    val base_code: String,
    val rates: Map<String, Double>
)