package com.example.agrocalendario.screens.detalledia

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.viewmodel.ActividadViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleDia(

    fecha: String,

    volver: () -> Unit,

    agregarActividad: () -> Unit,

    viewModel: ActividadViewModel = viewModel()

) {


    val actividades by viewModel.listaActividades.collectAsState()


    val actividadesDelDia = actividades.filter {

        it.fecha == fecha

    }



    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Actividades del día"
                    )

                }

            )

        }

    ) { padding ->



        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)

        ) {



            Text(

                text = "📅 Fecha: $fecha",

                style = MaterialTheme.typography.headlineSmall

            )



            Spacer(

                modifier = Modifier.height(20.dp)

            )



            if (actividadesDelDia.isEmpty()) {


                Card(

                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(

                        containerColor = Color(0xFFE8F5E9)

                    )

                ) {


                    Text(

                        text = "🌱 No hay actividades registradas para este día.",

                        modifier = Modifier.padding(20.dp)

                    )


                }


            } else {



                LazyColumn(

                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {



                    items(actividadesDelDia) { actividad ->



                        Card(

                            modifier = Modifier.fillMaxWidth(),

                            elevation = CardDefaults.cardElevation(

                                defaultElevation = 4.dp

                            )

                        ) {



                            Column(

                                modifier = Modifier.padding(16.dp)

                            ) {



                                Text(

                                    text = "🌱 ${actividad.actividad}",

                                    style = MaterialTheme.typography.titleLarge,

                                    color = Color(0xFF2E7D32)

                                )



                                Spacer(

                                    modifier = Modifier.height(8.dp)

                                )



                                Text(

                                    text = "📝 ${actividad.descripcion}"

                                )



                                Spacer(

                                    modifier = Modifier.height(8.dp)

                                )



                                Text(

                                    text =
                                        "🔔 Recordatorio: ${
                                            if (actividad.recordatorio)
                                                "Sí"
                                            else
                                                "No"
                                        }"

                                )



                                if (actividad.recordatorio) {



                                    Text(

                                        text =
                                            "🔁 Repetición: ${actividad.repeticion}"

                                    )



                                    if (actividad.proximaFecha.isNotEmpty()) {


                                        Text(

                                            text =
                                                "📅 Próxima fecha: ${actividad.proximaFecha}",

                                            color = Color(0xFF2E7D32)

                                        )


                                    }


                                }



                            }


                        }



                    }


                }



            }



            Spacer(

                modifier = Modifier.weight(1f)

            )



            Button(

                onClick = agregarActividad,

                modifier = Modifier.fillMaxWidth()

            ) {


                Text(

                    text = "➕ Agregar actividad"

                )


            }



            Spacer(

                modifier = Modifier.height(10.dp)

            )



            OutlinedButton(

                onClick = volver,

                modifier = Modifier.fillMaxWidth()

            ) {


                Text("Volver")


            }


        }


    }


}