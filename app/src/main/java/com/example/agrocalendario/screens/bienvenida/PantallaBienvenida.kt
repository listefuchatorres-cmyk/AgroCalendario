package com.example.agrocalendario.screens.bienvenida

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.agrocalendario.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ButtonDefaults


@Composable
fun PantallaBienvenida(
    irLogin: () -> Unit,
    irRegistro: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondo_agricultura),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "BIENVENIDO",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF558B2F)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "AgroCalendario🌱",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF558B2F)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Organiza tus actividades agrícolas fácilmente",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = irLogin,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF66BB6A)
                )

            ) {
                Text("Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = irRegistro,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF66BB6A)
                )
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White
                )
            }
        }
    }
}