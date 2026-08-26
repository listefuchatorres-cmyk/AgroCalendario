package com.example.agrocalendario.screens.configuracion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.agrocalendario.data.preferences.PreferenciasDataStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConfiguracion(
    volver: () -> Unit
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    // =========================
    // LEER DATOS DE DATASTORE
    // =========================

    val recordatoriosGuardados by
    PreferenciasDataStore
        .recordatoriosActivados(context)
        .collectAsState(initial = true)

    val horaGuardada by
    PreferenciasDataStore
        .horaRecordatorio(context)
        .collectAsState(initial = 18)

    val minutoGuardado by
    PreferenciasDataStore
        .minutoRecordatorio(context)
        .collectAsState(initial = 0)

    val diasGuardados by
    PreferenciasDataStore
        .diasAnticipacion(context)
        .collectAsState(initial = 1)

    val temaGuardado by
    PreferenciasDataStore
        .tema(context)
        .collectAsState(initial = "sistema")


    // =========================
    // ESTADOS TEMPORALES
    // =========================

    var recordatoriosActivados by
    remember(recordatoriosGuardados) {
        mutableStateOf(recordatoriosGuardados)
    }

    var hora by
    remember(horaGuardada) {
        mutableIntStateOf(horaGuardada)
    }

    var minuto by
    remember(minutoGuardado) {
        mutableIntStateOf(minutoGuardado)
    }

    var dias by
    remember(diasGuardados) {
        mutableIntStateOf(diasGuardados)
    }

    var tema by
    remember(temaGuardado) {
        mutableStateOf(temaGuardado)
    }


    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Configuración"
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = volver
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription =
                                "Volver"
                        )
                    }
                }
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(
                    rememberScrollState()
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            // =========================
            // ICONO
            // =========================

            Icon(

                imageVector =
                    Icons.Default.Notifications,

                contentDescription =
                    "Notificaciones",

                modifier =
                    Modifier.size(60.dp),

                tint =
                    Color(0xFF2E7D32)
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(

                text =
                    "Configuración de notificaciones",

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,

                color =
                    Color(0xFF1B5E20)
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            // =========================
            // RECORDATORIOS
            // =========================

            Card(

                modifier =
                    Modifier.fillMaxWidth()

            ) {

                Row(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween

                ) {

                    Column {

                        Text(

                            text =
                                "🔔 Recordatorios",

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                        Spacer(
                            modifier =
                                Modifier.height(5.dp)
                        )

                        Text(

                            text =
                                if (recordatoriosActivados)
                                    "Activados"
                                else
                                    "Desactivados",

                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium
                        )
                    }

                    Switch(

                        checked =
                            recordatoriosActivados,

                        onCheckedChange = {

                            recordatoriosActivados =
                                it

                        }
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            // =========================
            // AVISO PREVIO
            // =========================

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
                            "📅 Aviso previo",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    Text(

                        text =
                            "$dias día antes",

                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            // =========================
            // HORA
            // =========================

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
                            "🕕 Hora del recordatorio",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    Text(

                        text =
                            String.format(
                                "%02d:%02d",
                                hora,
                                minuto
                            ),

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall,

                        color =
                            Color(0xFF2E7D32)
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            // =========================
            // APARIENCIA
            // =========================

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
                            "🎨 Apariencia",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(

                        text =
                            "Selecciona el tema de la aplicación",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )


                    // MODO CLARO

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        RadioButton(

                            selected =
                                tema == "claro",

                            onClick = {

                                tema = "claro"

                            }
                        )

                        Text(
                            text = "☀️ Modo claro"
                        )
                    }


                    // MODO OSCURO

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        RadioButton(

                            selected =
                                tema == "oscuro",

                            onClick = {

                                tema = "oscuro"

                            }
                        )

                        Text(
                            text = "🌙 Modo oscuro"
                        )
                    }


                    // MODO SISTEMA

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        RadioButton(

                            selected =
                                tema == "sistema",

                            onClick = {

                                tema = "sistema"

                            }
                        )

                        Text(
                            text = "📱 Usar tema del dispositivo"
                        )
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )


            // =========================
            // GUARDAR
            // =========================

            Button(

                onClick = {

                    scope.launch {

                        // Guardar notificaciones

                        PreferenciasDataStore
                            .guardarConfiguracion(

                                context = context,

                                recordatoriosActivados =
                                    recordatoriosActivados,

                                hora =
                                    hora,

                                minuto =
                                    minuto,

                                diasAnticipacion =
                                    dias
                            )


                        // Guardar tema

                        PreferenciasDataStore
                            .guardarTema(

                                context = context,

                                tema = tema
                            )
                    }

                },

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF2E7D32)
                    )

            ) {

                Text(
                    text =
                        "Guardar configuración"
                )
            }


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            // =========================
            // VOLVER
            // =========================

            OutlinedButton(

                onClick =
                    volver,

                modifier =
                    Modifier.fillMaxWidth()

            ) {

                Text(
                    text =
                        "Volver"
                )
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )
        }
    }
}