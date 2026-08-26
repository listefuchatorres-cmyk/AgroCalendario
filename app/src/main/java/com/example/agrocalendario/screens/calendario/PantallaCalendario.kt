package com.example.agrocalendario.screens.calendario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.R
import com.example.agrocalendario.viewmodel.ActividadViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth


@Composable
fun PantallaCalendario(

    onDiaSeleccionado: (LocalDate) -> Unit,

    viewModel: ActividadViewModel = viewModel()

) {


    val actividades by viewModel.listaActividades.collectAsState()


    var mesActual by remember {

        mutableStateOf(
            YearMonth.now()
        )

    }


    var diaSeleccionado by remember {

        mutableStateOf(
            LocalDate.now()
        )

    }



    val primerDiaMes =
        mesActual.atDay(1)


    val diasDelMes =
        mesActual.lengthOfMonth()



    val desplazamiento =

        if (primerDiaMes.dayOfWeek == DayOfWeek.SUNDAY)

            6

        else

            primerDiaMes.dayOfWeek.value - 1



    val dias = mutableListOf<Int?>()



    repeat(desplazamiento) {

        dias.add(null)

    }



    for (i in 1..diasDelMes) {

        dias.add(i)

    }




    Box(

        modifier = Modifier
            .fillMaxSize()

    ) {



        // ==========================
        // FONDO DE AGRICULTURA
        // ==========================

        Image(

            painter = painterResource(
                id = R.drawable.fondo_agricultura
            ),

            contentDescription = null,

            modifier = Modifier
                .fillMaxSize(),

            contentScale = ContentScale.Crop

        )




        // Capa transparente encima del fondo

        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White.copy(
                        alpha = 0.35f
                    )
                )

        )




        Column(

            modifier = Modifier

                .fillMaxSize()

                .padding(16.dp)

        ) {



            // ==========================
            // CABECERA MES
            // ==========================


            MonthHeader(

                month = mesActual,


                onPrevious = {

                    mesActual =
                        mesActual.minusMonths(1)

                },


                onNext = {

                    mesActual =
                        mesActual.plusMonths(1)

                }

            )




            Spacer(

                modifier =
                    Modifier.height(18.dp)

            )






            // ==========================
            // DIAS SEMANA
            // ==========================


            Row(

                modifier =
                    Modifier.fillMaxWidth(),


                horizontalArrangement =
                    Arrangement.SpaceBetween

            ) {



                listOf(

                    "Lu",
                    "Ma",
                    "Mi",
                    "Ju",
                    "Vi",
                    "Sá",
                    "Do"

                ).forEach { nombre ->



                    Card(

                        modifier =
                            Modifier.size(40.dp),



                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    Color.White.copy(
                                        alpha = 0.85f
                                    )

                            )

                    ) {



                        Box(

                            modifier =
                                Modifier.fillMaxSize(),


                            contentAlignment =
                                Alignment.Center

                        ) {



                            Text(

                                text = nombre,

                                color =
                                    Color(0xFF2E7D32)

                            )

                        }



                    }


                }


            }





            Spacer(

                modifier =
                    Modifier.height(12.dp)

            )






            // ==========================
            // CALENDARIO
            // ==========================


            LazyVerticalGrid(

                columns =
                    GridCells.Fixed(7),


                modifier =
                    Modifier.fillMaxWidth()

            ) {



                items(dias) { dia ->




                    if (dia == null) {



                        Spacer(

                            modifier =
                                Modifier

                                    .padding(5.dp)

                                    .size(52.dp)

                        )



                    } else {



                        val fecha =
                            mesActual.atDay(dia)




                        val tieneActividad =

                            actividades.any {

                                it.fecha ==
                                        fecha.toString()

                            }

                        CalendarDay(

                            day = dia,


                            tieneTareas =
                                tieneActividad,



                            seleccionado =
                                fecha ==
                                        diaSeleccionado,



                            onClick = {


                                diaSeleccionado =
                                    fecha



                                onDiaSeleccionado(
                                    fecha
                                )


                            }

                        )



                    }



                }



            }






            Spacer(

                modifier =
                    Modifier.height(20.dp)

            )






            // ==========================
            // LEYENDA
            // ==========================


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



                Text(

                    text =
                        "🟢 Actividad registrada",


                    modifier =
                        Modifier.padding(12.dp),


                    color =
                        Color(0xFF2E7D32)

                )


            }




        }



    }
    
}