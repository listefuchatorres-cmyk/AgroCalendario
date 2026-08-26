package com.example.agrocalendario.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrocalendario.data.preferences.PreferenciasDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PreferenciasViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val _recordatoriosActivados =
        MutableStateFlow(true)

    val recordatoriosActivados: StateFlow<Boolean> =
        _recordatoriosActivados

    private val _hora =
        MutableStateFlow(18)

    val hora: StateFlow<Int> =
        _hora

    private val _minuto =
        MutableStateFlow(0)

    val minuto: StateFlow<Int> =
        _minuto

    private val _diasAnticipacion =
        MutableStateFlow(1)

    val diasAnticipacion: StateFlow<Int> =
        _diasAnticipacion


    init {
        cargarPreferencias()
    }


    private fun cargarPreferencias() {

        viewModelScope.launch {

            _recordatoriosActivados.value =
                PreferenciasDataStore
                    .recordatoriosActivados(context)
                    .first()

            _hora.value =
                PreferenciasDataStore
                    .horaRecordatorio(context)
                    .first()

            _minuto.value =
                PreferenciasDataStore
                    .minutoRecordatorio(context)
                    .first()

            _diasAnticipacion.value =
                PreferenciasDataStore
                    .diasAnticipacion(context)
                    .first()
        }
    }


    fun cambiarRecordatorios(
        activados: Boolean
    ) {

        _recordatoriosActivados.value =
            activados

        guardarPreferencias()
    }


    private fun guardarPreferencias() {

        viewModelScope.launch {

            PreferenciasDataStore
                .guardarConfiguracion(

                    context = context,

                    recordatoriosActivados =
                        _recordatoriosActivados.value,

                    hora =
                        _hora.value,

                    minuto =
                        _minuto.value,

                    diasAnticipacion =
                        _diasAnticipacion.value
                )
        }
    }
}