package com.example.agrocalendario.navigation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrocalendario.screens.actividad.PantallaEditarActividad
import com.example.agrocalendario.screens.actividad.PantallaMisActividades
import com.example.agrocalendario.screens.actividad.PantallaRegistrarActividad
import com.example.agrocalendario.screens.bienvenida.PantallaBienvenida
import com.example.agrocalendario.screens.calendario.PantallaCalendario
import com.example.agrocalendario.screens.detalledia.PantallaDetalleDia
import com.example.agrocalendario.screens.login.PantallaLogin
import com.example.agrocalendario.screens.perfil.PantallaPerfil
import com.example.agrocalendario.screens.principal.PantallaPrincipal
import com.example.agrocalendario.screens.registro.PantallaRegistro
import com.example.agrocalendario.viewmodel.ActividadViewModel
import com.example.agrocalendario.viewmodel.AuthViewModel
import com.example.agrocalendario.screens.configuracion.PantallaConfiguracion
import com.example.agrocalendario.screens.clima.PantallaClima


object Rutas {


    const val BIENVENIDA = "bienvenida"

    const val LOGIN = "login"

    const val REGISTRO = "registro"

    const val PRINCIPAL = "principal"

    const val CALENDARIO = "calendario"

    const val MIS_ACTIVIDADES = "mis_actividades"

    const val PERFIL = "perfil"

    const val CLIMA = "clima"

    const val CONFIGURACION = "configuracion"

    const val EDITAR_ACTIVIDAD = "editar_actividad/{id}"

    const val DETALLE_DIA = "detalle_dia/{fecha}"

    const val REGISTRAR_ACTIVIDAD = "registrar_actividad/{fecha}"

}



@Composable
fun NavGraph(

    authViewModel: AuthViewModel,

    googleLauncher: ActivityResultLauncher<Intent>,

    cambiarGoogleSuccess: ((() -> Unit) -> Unit)

) {


    val navController =
        rememberNavController()



    NavHost(

        navController = navController,

        startDestination = Rutas.BIENVENIDA

    ) {

        composable(Rutas.BIENVENIDA) {


            PantallaBienvenida(

                irLogin = {

                    navController.navigate(
                        Rutas.LOGIN
                    )

                },

                irRegistro = {

                    navController.navigate(
                        Rutas.REGISTRO
                    )

                }

            )


        }


        composable(Rutas.LOGIN) {


            PantallaLogin(

                irRegistro = {

                    navController.navigate(
                        Rutas.REGISTRO
                    )

                },


                irPrincipal = {

                    navController.navigate(
                        Rutas.PRINCIPAL
                    )

                },


                viewModel = authViewModel,


                iniciarGoogle = {

                    cambiarGoogleSuccess {

                        navController.navigate(
                            Rutas.PRINCIPAL
                        )

                    }


                    googleLauncher.launch(

                        authViewModel
                            .iniciarSesionGoogle(
                                navController.context
                            )

                    )

                }


            )


        }

        composable(Rutas.REGISTRO) {


            PantallaRegistro(

                irLogin = {

                    navController.navigate(
                        Rutas.LOGIN
                    )

                },


                irPrincipal = {

                    navController.navigate(
                        Rutas.PRINCIPAL
                    )

                }


            )


        }

        composable(Rutas.PRINCIPAL) {


            PantallaPrincipal(

                irCalendario = {

                    navController.navigate(
                        Rutas.CALENDARIO
                    )

                },


                irTareas = {

                    navController.navigate(
                        Rutas.MIS_ACTIVIDADES
                    )

                },

                irClima = {

                    navController.navigate(
                        Rutas.CLIMA
                    )

                },


                irPerfil = {

                    navController.navigate(
                        Rutas.PERFIL
                    )

                }


            )


        }

        // PERFIL

        composable(Rutas.PERFIL) {


            PantallaPerfil(

                volver = {

                    navController.popBackStack()

                },


                irLogin = {

                    navController.navigate(
                        Rutas.LOGIN
                    ) {

                        popUpTo(
                            Rutas.PRINCIPAL
                        ) {

                            inclusive = true

                        }

                    }

                },

                irConfiguracion = {

                    navController.navigate(
                        Rutas.CONFIGURACION
                    )

                }

            )


        }

        composable(Rutas.CLIMA) {

            PantallaClima(

                volver = {

                    navController.popBackStack()

                }

            )

        }

        // ==========================
        // CONFIGURACIÓN
        // ==========================

        composable(Rutas.CONFIGURACION) {

            PantallaConfiguracion(

                volver = {

                    navController.popBackStack()

                }

            )

        }

        // ==========================
        // MIS ACTIVIDADES
        // ==========================

        composable(Rutas.MIS_ACTIVIDADES) {


            PantallaMisActividades(

                volver = {

                    navController.popBackStack()

                },


                editarActividad = { actividad ->


                    navController.navigate(

                        "editar_actividad/${actividad.id}"

                    )


                }


            )


        }







        // ==========================
        // EDITAR ACTIVIDAD
        // ==========================

        composable(

            route = Rutas.EDITAR_ACTIVIDAD

        ) { backStackEntry ->



            val actividadViewModel: ActividadViewModel =
                viewModel()



            val actividades by actividadViewModel
                .listaActividades
                .collectAsState()



            val id =

                backStackEntry.arguments
                    ?.getString("id")



            val actividad =

                actividades.find {

                    it.id == id

                }





            if (actividad != null) {


                PantallaEditarActividad(

                    actividad = actividad,


                    volver = {

                        navController.popBackStack()

                    }

                )


            }


        }







        // ==========================
        // CALENDARIO
        // ==========================

        composable(Rutas.CALENDARIO) {


            PantallaCalendario(

                onDiaSeleccionado = { fecha ->


                    navController.navigate(

                        "detalle_dia/$fecha"

                    )


                }

            )


        }







        // ==========================
        // DETALLE DIA
        // ==========================

        composable(Rutas.DETALLE_DIA) { backStackEntry ->


            val fecha =

                backStackEntry.arguments
                    ?.getString("fecha")
                    ?: ""



            PantallaDetalleDia(

                fecha = fecha,


                volver = {

                    navController.popBackStack()

                },


                agregarActividad = {


                    navController.navigate(

                        "registrar_actividad/$fecha"

                    )


                }


            )


        }







        // ==========================
        // REGISTRAR ACTIVIDAD
        // ==========================

        composable(Rutas.REGISTRAR_ACTIVIDAD) { backStackEntry ->


            val fecha =

                backStackEntry.arguments
                    ?.getString("fecha")
                    ?: ""



            PantallaRegistrarActividad(

                fecha = fecha,


                volver = {

                    navController.popBackStack()

                }


            )


        }



    }


}