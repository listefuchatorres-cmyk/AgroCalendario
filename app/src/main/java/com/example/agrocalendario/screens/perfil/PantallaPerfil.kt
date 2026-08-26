package com.example.agrocalendario.screens.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.agrocalendario.R
import com.example.agrocalendario.viewmodel.PerfilViewModel

@Composable
fun PantallaPerfil(

    volver: () -> Unit,

    irLogin: () -> Unit,

    irConfiguracion: () -> Unit = {},

    viewModel: PerfilViewModel = viewModel()

) {

    val usuario by viewModel.usuario.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // FONDO
        Image(
            painter = painterResource(
                id = R.drawable.fondo_agricultura
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // CAPA TRANSPARENTE
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White.copy(alpha = 0.35f)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =========================
            // BOTÓN CONFIGURACIÓN
            // =========================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),

                horizontalArrangement = Arrangement.End
            ) {

                IconButton(
                    onClick = {
                        irConfiguracion()
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Settings,

                        contentDescription =
                            "Configuración",

                        tint =
                            Color(0xFF1B5E20),

                        modifier =
                            Modifier.size(30.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            // =========================
            // FOTO DE PERFIL
            // =========================

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFF66BB6A)
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                if (usuario?.foto?.isNotEmpty() == true) {

                    AsyncImage(

                        model = usuario?.foto,

                        contentDescription =
                            "Foto de perfil",

                        modifier =
                            Modifier.fillMaxSize(),

                        contentScale =
                            ContentScale.Crop
                    )

                } else {

                    Text(
                        text = "👤",

                        style =
                            MaterialTheme
                                .typography
                                .displayMedium
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            // =========================
            // TÍTULO
            // =========================

            Text(
                text = "Mi Perfil",

                style =
                    MaterialTheme
                        .typography
                        .headlineMedium,

                color =
                    Color(0xFF1B5E20)
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // =========================
            // TARJETA DE INFORMACIÓN
            // =========================

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(

                        containerColor =
                            Color.White.copy(
                                alpha = 0.85f
                            )
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    // NOMBRE

                    Text(

                        text = "Nombre",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        color =
                            Color(0xFF2E7D32)
                    )

                    Text(

                        text =
                            "${usuario?.nombre ?: ""} ${usuario?.apellido ?: ""}",

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // CORREO

                    Text(

                        text = "Correo",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        color =
                            Color(0xFF2E7D32)
                    )

                    Text(

                        text =
                            usuario?.correo ?: "",

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            // =========================
            // CERRAR SESIÓN
            // =========================

            Button(

                onClick = {

                    viewModel.cerrarSesion(
                        irLogin
                    )
                },

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFFD32F2F)
                    )
            ) {

                Text(

                    text =
                        "🚪 Cerrar sesión",

                    color =
                        Color.White
                )
            }

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            // =========================
            // VOLVER
            // =========================

            OutlinedButton(

                onClick =
                    volver,

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF2E7D32)
                    )
            ) {

                Text(

                    text =
                        "Volver",

                    color =
                        Color.White
                )
            }
        }
    }
}