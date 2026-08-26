package com.example.agrocalendario.screens.actividad

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.R
import com.example.agrocalendario.data.model.Actividad
import com.example.agrocalendario.ui.theme.MoradoEditar
import com.example.agrocalendario.ui.theme.RojoEliminar
import com.example.agrocalendario.ui.theme.VerdeAgro
import com.example.agrocalendario.viewmodel.ActividadViewModel


@Composable
fun PantallaMisActividades(

    volver: () -> Unit,

    editarActividad: (Actividad) -> Unit,

    viewModel: ActividadViewModel = viewModel()

) {


    val actividades by viewModel.listaActividades.collectAsState()



    Box(

        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(

            painter = painterResource(
                id = R.drawable.fondo_agricultura
            ),

            contentDescription = null,

            modifier = Modifier
                .fillMaxSize(),

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
                .padding(16.dp)

        ) {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(

                text = "🌱 Mis actividades 🌱",

                style =
                    MaterialTheme.typography.headlineLarge,

                fontSize =
                    30.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Color(0xFF1B5E20)

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            if (actividades.isEmpty()) {

                Box(

                    modifier =
                        Modifier.fillMaxSize(),

                    contentAlignment =
                        Alignment.Center

                ) {

                    Text(

                        text =
                            "No tienes actividades registradas.",

                        color =
                            Color.DarkGray

                    )


                }

            } else {


                LazyColumn(

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)

                ) {

                    items(actividades) { actividad ->

                        Card(

                            modifier =
                                Modifier.fillMaxWidth(),


                            ) {

                            Column(

                                modifier =
                                    Modifier.padding(16.dp)

                            ) {

                                Text(

                                    text =
                                        actividad.actividad,

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.Bold

                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(8.dp)
                                )

                                Text(
                                    "Descripción: ${actividad.descripcion}"
                                )

                                Text(
                                    "Fecha: ${actividad.fecha}"
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(10.dp)
                                )

                                if (actividad.realizada) {

                                    Text(

                                        text =
                                            "🟢 Realizada",

                                        color =
                                            VerdeAgro

                                    )

                                } else {

                                    Text(

                                        text =
                                            "🟡 Pendiente",

                                        color =
                                            Color(0xFFF9A825)

                                    )

                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(8.dp)
                                )

                                Text(

                                    if (actividad.recordatorio)

                                        "🔔 Recordatorio activado"

                                    else

                                        "🔕 Sin recordatorio"

                                )

                                Text(
                                    "Repetición: ${actividad.repeticion}"
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(15.dp)
                                )

                                // EDITAR

                                Button(

                                    onClick = {

                                        editarActividad(actividad)

                                    },


                                    modifier =
                                        Modifier.fillMaxWidth(),


                                    colors =
                                        ButtonDefaults.buttonColors(

                                            containerColor =
                                                MoradoEditar

                                        )

                                ) {

                                    Text(

                                        text =
                                            "✏️ Editar",

                                        color =
                                            Color.White

                                    )


                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(8.dp)
                                )

                                // CAMBIAR ESTADO

                                Button(

                                    onClick = {

                                        viewModel
                                            .cambiarEstadoActividad(
                                                actividad
                                            )

                                    },


                                    modifier =
                                        Modifier.fillMaxWidth(),


                                    colors =
                                        ButtonDefaults.buttonColors(

                                            containerColor =

                                                if (actividad.realizada)

                                                    Color(0xFFF9A825)

                                                else

                                                    VerdeAgro

                                        )

                                ) {

                                    Text(

                                        text =

                                            if (actividad.realizada)

                                                "🟡 Marcar pendiente"

                                            else

                                                "🟢 Marcar realizada",


                                        color =
                                            Color.White

                                    )

                                }

                                Spacer(
                                    modifier =
                                        Modifier.height(8.dp)
                                )

                                // ELIMINAR

                                Button(

                                    onClick = {

                                        viewModel
                                            .eliminarActividad(
                                                actividad.id
                                            )

                                    },


                                    modifier =
                                        Modifier.fillMaxWidth(),


                                    colors =
                                        ButtonDefaults.buttonColors(

                                            containerColor =
                                                RojoEliminar

                                        )

                                ) {

                                    Text(

                                        text =
                                            "🗑️ Eliminar",

                                        color =
                                            Color.White

                                    )

                                }

                            }

                        }

                    }

                }

            }

            Spacer(
                modifier =
                    Modifier.weight(1f)
            )

            Button(

                onClick = volver,


                modifier =
                    Modifier.fillMaxWidth()

            ) {

                Text(
                    "Volver"
                )

            }

        }

    }

}