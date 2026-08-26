package com.example.agrocalendario.data.repository

import com.example.agrocalendario.data.remote.ClimaResponse
import com.example.agrocalendario.data.remote.RetrofitClient

class ClimaRepository {

    private val api = RetrofitClient.climaApi

    suspend fun obtenerClima(
        latitud: Double,
        longitud: Double
    ): Result<ClimaResponse> {

        return try {

            val respuesta = api.obtenerClima(
                latitud = latitud,
                longitud = longitud
            )

            Result.success(respuesta)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}