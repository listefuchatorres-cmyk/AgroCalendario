package com.example.agrocalendario.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RepeticionChips(

    repeticionSeleccionada: String,

    onRepeticionSeleccionada: (String) -> Unit

) {

    val opciones = listOf(

        "No repetir",

        "8 días",

        "15 días",

        "30 días",

        "Personalizado"

    )

    FlowRow(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(8.dp),

        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        opciones.forEach { opcion ->

            FilterChip(

                selected = repeticionSeleccionada == opcion,

                onClick = {

                    onRepeticionSeleccionada(opcion)

                },

                label = {

                    Text(opcion)

                },

                colors = FilterChipDefaults.filterChipColors(

                    selectedContainerColor = Color(0xFF2E7D32),

                    selectedLabelColor = Color.White

                ),

                modifier = Modifier.padding(2.dp)

            )

        }

    }

}