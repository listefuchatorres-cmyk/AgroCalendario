package com.example.agrocalendario.notification

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.agrocalendario.MainActivity
import com.example.agrocalendario.R

class NotificacionWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {

        val titulo =
            inputData.getString("titulo")
                ?: "Actividad"

        val descripcion =
            inputData.getString("descripcion")
                ?: ""

        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent =
            PendingIntent.getActivity(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        val notificacion = NotificationCompat.Builder(
            applicationContext,
            NotificacionHelper.CHANNEL_ID
        )

            .setSmallIcon(R.drawable.ic_launcher_foreground)

            .setContentTitle("🌱 AgroCalendario")

            .setContentText(titulo)

            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(descripcion)
            )

            .setPriority(NotificationCompat.PRIORITY_HIGH)

            .setAutoCancel(true)

            .setContentIntent(pendingIntent)

            .build()

        val manager = NotificationManagerCompat.from(applicationContext)

        manager.notify(
            System.currentTimeMillis().toInt(),
            notificacion
        )

        return Result.success()

    }

}