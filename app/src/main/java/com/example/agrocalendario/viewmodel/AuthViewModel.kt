package com.example.agrocalendario.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrocalendario.data.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AuthViewModel : ViewModel() {


    private val repository = AuthRepository()


    private val _mensaje =
        MutableStateFlow<String?>(null)


    val mensaje: StateFlow<String?> =
        _mensaje




    // ==========================
    // REGISTRO
    // ==========================

    fun registrar(
        nombre: String,
        apellido: String,
        correo: String,
        clave: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {


        viewModelScope.launch {


            val resultado =
                repository.registrarUsuario(
                    nombre,
                    apellido,
                    correo,
                    clave
                )


            if (resultado.isSuccess) {


                _mensaje.value =
                    "Usuario creado correctamente"


                onSuccess()


            } else {


                val error =
                    resultado.exceptionOrNull()?.message
                        ?: "No se pudo registrar"


                _mensaje.value = error

                onError(error)

            }


        }


    }





    // ==========================
    // LOGIN CORREO
    // ==========================

    fun iniciarSesion(
        correo: String,
        contraseña: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {


        viewModelScope.launch {


            val resultado =
                repository.iniciarSesion(
                    correo,
                    contraseña
                )


            if (resultado.isSuccess) {


                _mensaje.value =
                    "Bienvenido"


                onSuccess()


            } else {


                val error =
                    resultado.exceptionOrNull()?.message
                        ?: "Error al iniciar sesión"


                _mensaje.value = error

                onError(error)


            }


        }


    }





    // ==========================
// ABRIR GOOGLE
// ==========================

    fun iniciarSesionGoogle(
        context: Context
    ): Intent {


        val clienteGoogle =
            repository.obtenerClienteGoogle(context)


        // Obliga a mostrar las cuentas disponibles
        clienteGoogle.signOut()


        return clienteGoogle.signInIntent

    }

    // ==========================
    // RESULTADO GOOGLE
    // ==========================

    fun manejarResultadoGoogle(
        data: Intent?,
        onSuccess: () -> Unit
    ) {


        viewModelScope.launch {


            try {


                val cuenta =

                    GoogleSignIn
                        .getSignedInAccountFromIntent(data)
                        .await()



                val token =
                    cuenta.idToken



                if (token != null) {


                    val resultado =

                        repository
                            .iniciarSesionGoogle(
                                token
                            )



                    if (resultado.isSuccess) {


                        _mensaje.value =
                            "Google correcto"


                        onSuccess()


                    } else {


                        _mensaje.value =
                            "Error Firebase Google"


                    }



                } else {


                    _mensaje.value =
                        "Token Google vacío"


                }



            } catch (e: Exception) {


                _mensaje.value =
                    e.message ?: "Error Google"


            }


        }


    }





    // ==========================
    // CERRAR SESIÓN
    // ==========================

    fun cerrarSesion() {

        repository.cerrarSesion()

    }


}