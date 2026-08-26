package com.example.agrocalendario.notification

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.agrocalendario.data.preferences.PreferenciasDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit

object ProgramadorNotificaciones {

    fun programarNotificacion(
        context: Context,
        idActividad: String,
        titulo: String,
        descripcion: String,
        fecha: String
    ) {

        try {

            // Obtener configuración guardada en DataStore
            val recordatoriosActivados =
                runBlocking {
                    PreferenciasDataStore
                        .recordatoriosActivados(context)
                        .first()
                }

            // Si los recordatorios están desactivados,
            // no se programa ninguna notificación.
            if (!recordatoriosActivados) {
                return
            }

            // Hora configurada en DataStore
            val hora =
                runBlocking {
                    PreferenciasDataStore
                        .horaRecordatorio(context)
                        .first()
                }

            val minuto =
                runBlocking {
                    PreferenciasDataStore
                        .minutoRecordatorio(context)
                        .first()
                }

            // Días de anticipación configurados
            val diasAnticipacion =
                runBlocking {
                    PreferenciasDataStore
                        .diasAnticipacion(context)
                        .first()
                }

            // Fecha de la actividad
            val fechaActividad =
                LocalDate.parse(fecha)

            // Calcular fecha del aviso
            val fechaAviso =
                fechaActividad.minusDays(
                    diasAnticipacion.toLong()
                )

            // Crear fecha y hora del aviso
            val fechaHoraAviso =
                LocalDateTime.of(
                    fechaAviso,
                    LocalTime.of(
                        hora,
                        minuto
                    )
                )

            val ahora =
                LocalDateTime.now()

            val minutosRestantes =
                Duration.between(
                    ahora,
                    fechaHoraAviso
                ).toMinutes()

            // Si la fecha del aviso ya pasó,
            // no se programa.
            if (minutosRestantes <= 0) {
                return
            }

            val datos =
                workDataOf(
                    "titulo" to titulo,
                    "descripcion" to descripcion
                )

            val notificacion =
                OneTimeWorkRequestBuilder<NotificacionWorker>()

                    .setInputData(datos)

                    .setInitialDelay(
                        minutosRestantes,
                        TimeUnit.MINUTES
                    )

                    .addTag(idActividad)

                    .build()

            WorkManager
                .getInstance(context)
                .enqueue(notificacion)

        } catch (e: Exception) {

            e.printStackTrace()

        }
    }

    fun cancelarNotificacion(
        context: Context,
        idActividad: String
    ) {

        WorkManager
            .getInstance(context)
            .cancelAllWorkByTag(
                idActividad
            )
    }
}