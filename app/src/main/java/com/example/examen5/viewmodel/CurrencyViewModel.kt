package com.example.examen5.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.examen5.network.RetroFitClient

class CurrencyViewModel : ViewModel() {
    // Variables de estado para la interfaz
    var amountText by mutableStateOf("")
    var selectedCurrency by mutableStateOf("GTQ")
    var conversionResult by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    // Guardaremos las tasas de cambio aquí una vez que la API responda
    private var exchangeRates: Map<String, Double> = emptyMap()

    // Lista de monedas soportadas por nuestra app
    val supportedCurrencies = listOf("GTQ", "HNL", "NIO", "MXN")

    // Función para conectar a la API
    fun fetchRatesAndCalculate() {
        // Validamos que el monto no esté vacío y sea un número válido antes de llamar a la red
        val amountValue = amountText.toDoubleOrNull()

        if (amountValue == null) {
            errorMessage = "Por favor ingresa un monto válido."
            conversionResult = ""
        } else {
            errorMessage = ""
            // Usamos viewModelScope para ejecutar la llamada en segundo plano
            viewModelScope.launch {
                isLoading = true
                try {
                    // Llamamos a la API
                    val response = RetroFitClient.apiService.getExchangeRates()

                    if (response.result == "success") {
                        exchangeRates = response.rates
                        calculateConversion(amountValue)
                    } else {
                        errorMessage = "Error en la respuesta de la API."
                    }
                } catch (e: Exception) {
                    // Si no hay internet o falla la conexión
                    errorMessage = "Error de conexión. Revisa tu internet."
                } finally {
                    isLoading = false
                }
            }
        }
    }

    // Función para realizar la matemática de la conversión
    private fun calculateConversion(amount: Double) {
        if (exchangeRates.isEmpty()) {
            errorMessage = "Las tasas de cambio no están disponibles."
        } else {
            val rate = exchangeRates[selectedCurrency]

            if (rate != null) {
                // Multiplicamos el monto en USD por la tasa de cambio de la moneda
                val total = amount * rate
                // Formateamos para que muestre 2 decimales
                conversionResult = String.format("%.2f %s", total, selectedCurrency)
            } else {
                errorMessage = "Moneda no encontrada en la API."
            }
        }
    }
}