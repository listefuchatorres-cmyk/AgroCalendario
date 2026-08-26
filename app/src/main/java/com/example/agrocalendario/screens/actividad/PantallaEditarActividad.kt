package com.example.agrocalendario.screens.actividad

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.data.model.Actividad
import com.example.agrocalendario.screens.components.RepeticionChips
import com.example.agrocalendario.ui.theme.VerdeAgro
import com.example.agrocalendario.viewmodel.ActividadViewModel
import kotlinx.coroutines.delay

@Composable
fun PantallaEditarActividad(

    actividad: Actividad,

    volver: () -> Unit,

    viewModel: ActividadViewModel = viewModel()

) {

    val contexto = LocalContext.current

    var nombre by remember {
        mutableStateOf(actividad.actividad)
    }

    var descripcion by remember {
        mutableStateOf(actividad.descripcion)
    }

    var fecha by remember {
        mutableStateOf(actividad.fecha)
    }

    var recordatorio by remember {
        mutableStateOf(actividad.recordatorio)
    }

    var repeticion by remember {

        mutableStateOf(

            if (actividad.diasPersonalizados > 0)
                "Personalizado"
            else
                actividad.repeticion

        )

    }

    var diasPersonalizados by remember {

        mutableStateOf(

            if (actividad.diasPersonalizados == 0)
                ""
            else
                actividad.diasPersonalizados.toString()

        )

    }

    var guardado by remember {

        mutableStateOf(false)

    }

    LaunchedEffect(guardado) {

        if (guardado) {

            Toast.makeText(

                contexto,

                "✅ Actividad actualizada correctamente",

                Toast.LENGTH_SHORT

            ).show()

            delay(1000)

            volver()

        }

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)

    ) {

        Text(

            text = "Editar actividad",

            style = MaterialTheme.typography.headlineMedium,

            color = VerdeAgro

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = nombre,

            onValueChange = {

                nombre = it

            },

            label = {

                Text("Actividad")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(15.dp))

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

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(

            value = fecha,

            onValueChange = {

                fecha = it

            },

            label = {

                Text("Fecha (yyyy-MM-dd)")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Text("Recordatorio")

            Switch(

                checked = recordatorio,

                onCheckedChange = {

                    recordatorio = it

                }

            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "🔁 Repetición",

            style = MaterialTheme.typography.titleMedium

        )

        Spacer(modifier = Modifier.height(10.dp))

        RepeticionChips(

            repeticionSeleccionada = repeticion,

            onRepeticionSeleccionada = {

                repeticion = it

            }

        )

        if (repeticion == "Personalizado") {

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(

                value = diasPersonalizados,

                onValueChange = {

                    diasPersonalizados = it

                },

                label = {

                    Text("Cantidad de días")

                },

                modifier = Modifier.fillMaxWidth()

            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(

            onClick = {

                val repeticionFinal =

                    if (repeticion == "Personalizado") {

                        "Cada ${diasPersonalizados.ifBlank { "0" }} días"

                    } else {

                        repeticion

                    }

                val actividadActualizada = actividad.copy(

                    actividad = nombre,

                    descripcion = descripcion,

                    fecha = fecha,

                    recordatorio = recordatorio,

                    repeticion = repeticionFinal,

                    diasPersonalizados =
                        diasPersonalizados.toIntOrNull() ?: 0

                )

                viewModel.actualizarActividad(

                    actividad = actividadActualizada,

                    onSuccess = {

                        guardado = true

                    }

                )

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor = VerdeAgro

            )

        ) {

            Text(

                text = "💾 Guardar cambios",

                color = Color.White

            )

        }

        Spacer(

            modifier = Modifier.height(12.dp)

        )

        OutlinedButton(

            onClick = {

                volver()

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)

        ) {

            Text(

                text = "Cancelar"

            )

        }

    }

}