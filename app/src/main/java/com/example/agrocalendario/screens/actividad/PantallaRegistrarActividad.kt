package com.example.agrocalendario.screens.actividad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.notification.ProgramadorNotificaciones
import com.example.agrocalendario.screens.components.ActividadChips
import com.example.agrocalendario.screens.components.RepeticionChips
import com.example.agrocalendario.viewmodel.ActividadViewModel
import com.example.agrocalendario.ui.theme.VerdeAgro
import kotlinx.coroutines.delay


@Composable
fun PantallaRegistrarActividad(

    fecha: String,

    volver: () -> Unit,

    viewModel: ActividadViewModel = viewModel()

) {


    val context = LocalContext.current



    var actividad by remember {

        mutableStateOf("")

    }


    var otraActividad by remember {

        mutableStateOf("")

    }


    var descripcion by remember {

        mutableStateOf("")

    }


    var recordatorio by remember {

        mutableStateOf(false)

    }


    var repeticion by remember {

        mutableStateOf("No repetir")

    }


    var diasPersonalizados by remember {

        mutableStateOf("")

    }



    val mensaje by viewModel.mensaje.collectAsState()

    LaunchedEffect(mensaje) {

        if (mensaje == "Actividad registrada correctamente") {

            delay(1000)

            volver()

        }

    }



    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(Color(0xFFF4F9F4))

            .verticalScroll(rememberScrollState())

            .padding(20.dp)

    ) {



        Text(

            text = "🌱 Registrar actividad",

            style = MaterialTheme.typography.headlineSmall

        )



        Spacer(

            modifier = Modifier.height(15.dp)

        )



        Text(

            text = "📅 Fecha seleccionada",

            style = MaterialTheme.typography.titleMedium

        )



        Text(

            text = fecha,

            color = Color(0xFF2E7D32),

            style = MaterialTheme.typography.titleLarge

        )





        Spacer(

            modifier = Modifier.height(20.dp)

        )





        Text(

            text = "🌱 Actividad",

            style = MaterialTheme.typography.titleMedium

        )



        Spacer(

            modifier = Modifier.height(10.dp)

        )




        ActividadChips(

            actividadSeleccionada = actividad,

            onActividadSeleccionada = {

                actividad = it

            }

        )





        if (actividad == "Otra...") {



            Spacer(

                modifier = Modifier.height(15.dp)

            )




            OutlinedTextField(

                value = otraActividad,

                onValueChange = {

                    otraActividad = it

                },

                label = {

                    Text("Nombre de la actividad")

                },

                modifier = Modifier.fillMaxWidth()

            )


        }





        Spacer(

            modifier = Modifier.height(20.dp)

        )





        OutlinedTextField(

            value = descripcion,

            onValueChange = {

                descripcion = it

            },

            label = {

                Text("Descripción")

            },

            modifier = Modifier

                .fillMaxWidth()

                .height(120.dp)

        )





        Spacer(

            modifier = Modifier.height(20.dp)

        )





        Text(

            text = "🔔 Activar recordatorio",

            style = MaterialTheme.typography.titleMedium

        )





        Switch(

            checked = recordatorio,

            onCheckedChange = {

                recordatorio = it

            }

        )





        if(recordatorio){



            Spacer(

                modifier = Modifier.height(20.dp)

            )



            Text(

                text = "🔁 Repetición",

                style = MaterialTheme.typography.titleMedium

            )



            Spacer(

                modifier = Modifier.height(10.dp)

            )



            RepeticionChips(

                repeticionSeleccionada = repeticion,

                onRepeticionSeleccionada = {

                    repeticion = it

                }

            )



            if(repeticion == "Personalizado"){



                Spacer(

                    modifier = Modifier.height(15.dp)

                )


                OutlinedTextField(

                    value = diasPersonalizados,

                    onValueChange = {

                        diasPersonalizados = it

                    },

                    label = {

                        Text("Cantidad de días")

                    },

                    keyboardOptions = KeyboardOptions(

                        keyboardType = KeyboardType.Number

                    ),

                    modifier = Modifier.fillMaxWidth()

                )


            }


        }

        Spacer(

            modifier = Modifier.height(25.dp)

        )



        mensaje?.let {


            Text(

                text = it,

                color = Color.Red

            )


            Spacer(

                modifier = Modifier.height(10.dp)

            )


        }


        Button(

            onClick = {

                val actividadFinal =
                    if (actividad == "Otra...")
                        otraActividad
                    else
                        actividad


                val repeticionFinal =

                    if (repeticion == "Personalizado") {

                        "Cada ${diasPersonalizados} días"

                    } else {

                        repeticion

                    }


                viewModel.guardarActividad(

                    actividad = actividadFinal,

                    descripcion = descripcion,

                    fecha = fecha,

                    recordatorio = recordatorio,

                    repeticion = repeticionFinal,

                    diasPersonalizados =
                        diasPersonalizados.toIntOrNull() ?: 0

                )

            },

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeAgro
            ),

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(
                text = "Guardar actividad",
                color = Color.White
            )

        }





        Spacer(

            modifier = Modifier.height(10.dp)

        )





        OutlinedButton(


            onClick = volver,


            modifier = Modifier.fillMaxWidth()


        ) {



            Text("Cancelar")


        }



    }


}