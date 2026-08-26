package com.example.agrocalendario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrocalendario.data.remote.ClimaResponse
import com.example.agrocalendario.data.repository.ClimaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClimaViewModel : ViewModel() {

    private val repository = ClimaRepository()

    private val _clima =
        MutableStateFlow<ClimaResponse?>(null)

    val clima: StateFlow<ClimaResponse?> =
        _clima

    private val _cargando =
        MutableStateFlow(false)

    val cargando: StateFlow<Boolean> =
        _cargando

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> =
        _error


    fun obtenerClima(
        latitud: Double,
        longitud: Double
    ) {

        viewModelScope.launch {

            _cargando.value = true
            _error.value = null

            val resultado =
                repository.obtenerClima(
                    latitud = latitud,
                    longitud = longitud
                )

            if (resultado.isSuccess) {

                _clima.value =
                    resultado.getOrNull()

            } else {

                _error.value =
                    "No se pudo obtener el clima"

            }

            _cargando.value = false
        }
    }
}