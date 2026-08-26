package com.example.agrocalendario

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.agrocalendario.navigation.NavGraph
import com.example.agrocalendario.notification.NotificacionHelper
import com.example.agrocalendario.ui.theme.AgroCalendarioTheme
import com.example.agrocalendario.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private lateinit var authViewModel: AuthViewModel

    private var googleSuccess: () -> Unit = {}

    private val solicitarPermisoNotificaciones =

        registerForActivityResult(

            ActivityResultContracts.RequestPermission()

        ) { }

    private val googleLauncher =

        registerForActivityResult(

            ActivityResultContracts.StartActivityForResult()

        ) { resultado ->

            authViewModel.manejarResultadoGoogle(

                resultado.data

            ) {

                googleSuccess()

            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        authViewModel =
            ViewModelProvider(this)[AuthViewModel::class.java]

        // Crear canal de notificaciones
        NotificacionHelper.crearCanal(this)

        // Pedir permiso únicamente en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                solicitarPermisoNotificaciones.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )

            }

        }

        setContent {

            AgroCalendarioTheme {

                NavGraph(

                    authViewModel = authViewModel,

                    googleLauncher = googleLauncher,

                    cambiarGoogleSuccess = { accion ->

                        googleSuccess = accion

                    }

                )

            }

        }

    }

}