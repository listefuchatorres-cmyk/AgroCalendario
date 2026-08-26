package com.example.agrocalendario.screens.calendario

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CalendarDay(

    day: Int?,

    tieneTareas: Boolean,

    seleccionado: Boolean,

    onClick: () -> Unit

) {


    Card(

        modifier = Modifier
            .padding(4.dp)
            .size(48.dp)
            .clickable(enabled = day != null) {

                onClick()

            },

        colors = CardDefaults.cardColors(

            containerColor =

                if (seleccionado)

                    Color(0xFF2E7D32)

                else

                    Color.White

        ),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 3.dp

        )

    ) {


        Box(

            modifier = Modifier.fillMaxSize(),

            contentAlignment = Alignment.Center

        ) {


            if (day != null) {


                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,

                    verticalArrangement = Arrangement.Center

                ) {


                    Text(

                        text = day.toString(),

                        color =

                            if (seleccionado)

                                Color.White

                            else

                                Color.Black

                    )


                    if (tieneTareas) {


                        Spacer(

                            modifier = Modifier.height(3.dp)

                        )


                        Box(

                            modifier = Modifier

                                .size(7.dp)

                                .background(

                                    Color(0xFF43A047),

                                    CircleShape

                                )

                        )


                    }


                }


            }


        }


    }


}