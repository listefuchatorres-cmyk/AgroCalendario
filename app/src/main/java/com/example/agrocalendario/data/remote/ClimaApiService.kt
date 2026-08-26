package com.example.agrocalendario.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApiService {

    @GET("v1/forecast")
    suspend fun obtenerClima(
        @Query("latitude") latitud: Double,
        @Query("longitude") longitud: Double,
        @Query("current") current: String =
            "temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m"
    ): ClimaResponse
}