package com.example.agrocalendario.screens.clima

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.viewmodel.ClimaViewModel

@Composable
fun PantallaClima(
    volver: () -> Unit,
    viewModel: ClimaViewModel = viewModel()
) {

    val clima by viewModel.clima.collectAsState()

    val cargando by viewModel.cargando.collectAsState()

    val error by viewModel.error.collectAsState()


    LaunchedEffect(Unit) {

        viewModel.obtenerClima(
            latitud = 0.3478,
            longitud = -78.1328
        )
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(

            text = "🌦️ Clima agrícola",

            style =
                MaterialTheme.typography.headlineSmall
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        if (cargando) {

            CircularProgressIndicator()

        }


        error?.let {

            Text(
                text = it
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Button(

                onClick = {

                    viewModel.obtenerClima(
                        latitud = 0.3478,
                        longitud = -78.1328
                    )

                }

            ) {

                Text("Reintentar")

            }
        }


        clima?.let { respuesta ->

            Card(

                modifier =
                    Modifier.fillMaxWidth()

            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)

                ) {

                    Text(
                        text =
                            "🌡️ Temperatura: " +
                                    "${respuesta.current.temperatura} °C"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            "💧 Humedad: " +
                                    "${respuesta.current.humedad} %"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            "🌧️ Precipitación: " +
                                    "${respuesta.current.precipitacion} mm"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            "💨 Viento: " +
                                    "${respuesta.current.velocidadViento} km/h"
                    )
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        Button(

            onClick = volver,

            modifier =
                Modifier.fillMaxWidth()

        ) {

            Text("Volver")

        }
    }
}