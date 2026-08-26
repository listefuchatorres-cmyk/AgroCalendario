package com.example.agrocalendario.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificacionHelper {

    const val CHANNEL_ID = "agrocalendario_recordatorios"

    fun crearCanal(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val canal = NotificationChannel(
                CHANNEL_ID,
                "Recordatorios AgroCalendario",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {

                description =
                    "Notificaciones de actividades agrícolas"

            }

            val manager =
                context.getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(canal)

        }

    }

}