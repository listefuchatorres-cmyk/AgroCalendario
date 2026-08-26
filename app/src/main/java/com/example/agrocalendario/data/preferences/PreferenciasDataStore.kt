package com.example.agrocalendario.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "agrocalendario_preferencias"
)

object PreferenciasDataStore {

    // =========================
    // NOTIFICACIONES
    // =========================

    private val RECORDATORIOS_ACTIVADOS =
        booleanPreferencesKey("recordatorios_activados")

    private val HORA_RECORDATORIO =
        intPreferencesKey("hora_recordatorio")

    private val MINUTO_RECORDATORIO =
        intPreferencesKey("minuto_recordatorio")

    private val DIAS_ANTICIPACION =
        intPreferencesKey("dias_anticipacion")


    // =========================
    // TEMA
    // =========================

    private val TEMA =
        stringPreferencesKey("tema")


    // =========================
    // LEER NOTIFICACIONES
    // =========================

    fun recordatoriosActivados(
        context: Context
    ): Flow<Boolean> {

        return context.dataStore.data.map { preferencias ->

            preferencias[RECORDATORIOS_ACTIVADOS] ?: true

        }
    }


    fun horaRecordatorio(
        context: Context
    ): Flow<Int> {

        return context.dataStore.data.map { preferencias ->

            preferencias[HORA_RECORDATORIO] ?: 18

        }
    }


    fun minutoRecordatorio(
        context: Context
    ): Flow<Int> {

        return context.dataStore.data.map { preferencias ->

            preferencias[MINUTO_RECORDATORIO] ?: 0

        }
    }


    fun diasAnticipacion(
        context: Context
    ): Flow<Int> {

        return context.dataStore.data.map { preferencias ->

            preferencias[DIAS_ANTICIPACION] ?: 1

        }
    }


    // =========================
    // LEER TEMA
    // =========================

    fun tema(
        context: Context
    ): Flow<String> {

        return context.dataStore.data.map { preferencias ->

            preferencias[TEMA] ?: "sistema"

        }
    }


    // =========================
    // GUARDAR CONFIGURACIÓN
    // =========================

    suspend fun guardarConfiguracion(
        context: Context,
        recordatoriosActivados: Boolean,
        hora: Int,
        minuto: Int,
        diasAnticipacion: Int
    ) {

        context.dataStore.edit { preferencias ->

            preferencias[RECORDATORIOS_ACTIVADOS] =
                recordatoriosActivados

            preferencias[HORA_RECORDATORIO] =
                hora

            preferencias[MINUTO_RECORDATORIO] =
                minuto

            preferencias[DIAS_ANTICIPACION] =
                diasAnticipacion

        }
    }


    // =========================
    // GUARDAR TEMA
    // =========================

    suspend fun guardarTema(
        context: Context,
        tema: String
    ) {

        context.dataStore.edit { preferencias ->

            preferencias[TEMA] =
                tema

        }
    }

}