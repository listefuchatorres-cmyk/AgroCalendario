package com.example.agrocalendario.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrocalendario.data.model.Actividad
import com.example.agrocalendario.data.repository.ActividadRepository
import com.example.agrocalendario.notification.ProgramadorNotificaciones
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActividadViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ActividadRepository()

    private val _listaActividades =
        MutableStateFlow<List<Actividad>>(emptyList())

    val listaActividades: StateFlow<List<Actividad>> =
        _listaActividades

    private val _mensaje =
        MutableStateFlow<String?>(null)

    val mensaje: StateFlow<String?> =
        _mensaje

    private val _cargando =
        MutableStateFlow(false)

    val cargando: StateFlow<Boolean> =
        _cargando

    private var escuchando = false

    init {
        obtenerActividades()
    }

    // ==========================
    // GUARDAR ACTIVIDAD
    // ==========================

    fun guardarActividad(
        actividad: String,
        descripcion: String,
        fecha: String,
        recordatorio: Boolean,
        repeticion: String,
        diasPersonalizados: Int,
        onSuccess: () -> Unit = {}
    ) {

        if (actividad.isBlank()) {
            _mensaje.value =
                "Seleccione una actividad"
            return
        }

        if (descripcion.isBlank()) {
            _mensaje.value =
                "Ingrese una descripción"
            return
        }

        val nuevaActividad = Actividad(

            actividad = actividad,

            descripcion = descripcion,

            fecha = fecha,

            recordatorio = recordatorio,

            // El aviso previo depende
            // de si el recordatorio está activado
            avisoPrevio = recordatorio,

            repeticion = repeticion,

            diasPersonalizados = diasPersonalizados,

            proximaFecha =
                calcularProximaFecha(
                    fecha,
                    repeticion,
                    diasPersonalizados
                )
        )

        viewModelScope.launch {

            _cargando.value = true

            val resultado =
                repository.guardarActividad(
                    nuevaActividad
                )

            if (resultado.isSuccess) {

                // Obtener la actividad guardada
                // con el ID generado por Firebase
                val actividadGuardada =
                    resultado.getOrNull()

                // ==========================
                // PROGRAMAR NOTIFICACIÓN
                // ==========================

                if (
                    recordatorio &&
                    actividadGuardada != null
                ) {

                    ProgramadorNotificaciones
                        .programarNotificacion(

                            context =
                                getApplication<Application>(),

                            idActividad =
                                actividadGuardada.id,

                            titulo =
                                actividadGuardada.actividad,

                            descripcion =
                                actividadGuardada.descripcion,

                            fecha =
                                actividadGuardada.fecha
                        )
                }

                _mensaje.value =
                    "Actividad registrada correctamente"

                onSuccess()

            } else {

                _mensaje.value =
                    resultado
                        .exceptionOrNull()
                        ?.message
            }

            _cargando.value = false
        }
    }

    // ==========================
    // ACTUALIZAR ACTIVIDAD
    // ==========================

    fun actualizarActividad(
        actividad: Actividad,
        onSuccess: () -> Unit = {}
    ) {

        viewModelScope.launch {

            _cargando.value = true

            val resultado =
                repository.actualizarActividad(
                    actividad
                )

            if (resultado.isSuccess) {

                // Cancelar notificación anterior
                ProgramadorNotificaciones
                    .cancelarNotificacion(

                        context =
                            getApplication<Application>(),

                        idActividad =
                            actividad.id
                    )

                // Programar nuevamente
                // si el recordatorio está activado
                if (actividad.recordatorio) {

                    ProgramadorNotificaciones
                        .programarNotificacion(

                            context =
                                getApplication<Application>(),

                            idActividad =
                                actividad.id,

                            titulo =
                                actividad.actividad,

                            descripcion =
                                actividad.descripcion,

                            fecha =
                                actividad.fecha
                        )
                }

                _mensaje.value =
                    "Actividad actualizada correctamente"

                onSuccess()

            } else {

                _mensaje.value =
                    resultado
                        .exceptionOrNull()
                        ?.message
            }

            _cargando.value = false
        }
    }

    // ==========================
    // CAMBIAR ESTADO
    // ==========================

    fun cambiarEstadoActividad(
        actividad: Actividad
    ) {

        val nuevaActividad =
            actividad.copy(
                realizada =
                    !actividad.realizada
            )

        viewModelScope.launch {

            val resultado =
                repository.actualizarActividad(
                    nuevaActividad
                )

            if (resultado.isSuccess) {

                _mensaje.value =
                    if (nuevaActividad.realizada)

                        "Actividad realizada ✅"

                    else

                        "Actividad pendiente"
            }
        }
    }

    // ==========================
    // ELIMINAR
    // ==========================

    fun eliminarActividad(
        id: String
    ) {

        viewModelScope.launch {

            val resultado =
                repository.eliminarActividad(id)

            if (resultado.isSuccess) {

                ProgramadorNotificaciones
                    .cancelarNotificacion(

                        context =
                            getApplication<Application>(),

                        idActividad =
                            id
                    )
            }
        }
    }

    // ==========================
    // OBTENER ACTIVIDADES
    // ==========================

    fun obtenerActividades() {

        if (escuchando)
            return

        escuchando = true

        viewModelScope.launch {

            repository
                .escucharActividades()
                .collect { lista ->

                    _listaActividades.value =
                        lista
                }
        }
    }

    // ==========================
    // CALCULAR PRÓXIMA FECHA
    // ==========================

    private fun calcularProximaFecha(
        fecha: String,
        repeticion: String,
        diasPersonalizados: Int
    ): String {

        if (repeticion == "No repetir")
            return ""

        val formato =
            DateTimeFormatter
                .ofPattern("yyyy-MM-dd")

        val fechaInicial =
            LocalDate.parse(
                fecha,
                formato
            )

        val dias =
            when {

                repeticion == "8 días" ->
                    8

                repeticion == "15 días" ->
                    15

                repeticion == "30 días" ->
                    30

                repeticion == "Personalizado" ->
                    diasPersonalizados

                repeticion.startsWith("Cada ") ->
                    repeticion
                        .removePrefix("Cada ")
                        .removeSuffix(" días")
                        .toIntOrNull()
                        ?: 0

                else ->
                    0
            }

        if (dias <= 0)
            return ""

        return fechaInicial
            .plusDays(
                dias.toLong()
            )
            .format(formato)
    }

    // ==========================
    // LIMPIAR MENSAJE
    // ==========================

    fun limpiarMensaje() {
        _mensaje.value = null
    }
}