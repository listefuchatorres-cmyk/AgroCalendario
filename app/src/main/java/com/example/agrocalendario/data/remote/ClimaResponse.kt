package com.example.agrocalendario.data.remote

import com.google.gson.annotations.SerializedName

data class ClimaResponse(

    @SerializedName("current")
    val current: ClimaActual
)

data class ClimaActual(

    @SerializedName("temperature_2m")
    val temperatura: Double,

    @SerializedName("relative_humidity_2m")
    val humedad: Double,

    @SerializedName("precipitation")
    val precipitacion: Double,

    @SerializedName("wind_speed_10m")
    val velocidadViento: Double
)