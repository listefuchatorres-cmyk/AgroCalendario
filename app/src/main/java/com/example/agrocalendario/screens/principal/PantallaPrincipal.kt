package com.example.agrocalendario.screens.principal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrocalendario.R
import com.example.agrocalendario.ui.theme.VerdeAgro

@Composable
fun PantallaPrincipal(
    irCalendario: () -> Unit,
    irTareas: () -> Unit,
    irClima: () -> Unit,
    irPerfil: () -> Unit
) {

    Image(
        painter = painterResource(R.drawable.fondo_agricultura),
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
            text = "Bienvenido a AgroCalendario 🌱",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        // =========================
        // CALENDARIO
        // =========================

        Button(

            onClick = irCalendario,

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeAgro
            ),

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Calendario")

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // MIS ACTIVIDADES
        // =========================

        Button(

            onClick = irTareas,

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeAgro
            ),

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Mis Actividades")

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // CLIMA
        // =========================

        Button(

            onClick = irClima,

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeAgro
            ),

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("🌦️ Clima agrícola")

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // PERFIL
        // =========================

        Button(

            onClick = irPerfil,

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeAgro
            ),

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Perfil")

        }

    }
}