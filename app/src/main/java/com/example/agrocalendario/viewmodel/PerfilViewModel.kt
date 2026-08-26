package com.example.agrocalendario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrocalendario.data.model.Usuario
import com.example.agrocalendario.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PerfilViewModel : ViewModel() {


    private val repository = AuthRepository()

    // DATOS DEL USUARIO

    private val _usuario =
        MutableStateFlow<Usuario?>(null)

    val usuario: StateFlow<Usuario?> =
        _usuario

    // MENSAJE
    private val _mensaje =
        MutableStateFlow<String?>(null)

    val mensaje: StateFlow<String?> =
        _mensaje

    // CARGAR PERFIL

    init {

        cargarPerfil()

    }

    fun cargarPerfil(){

        viewModelScope.launch {

            val resultado =
                repository.obtenerPerfil()

            if(resultado != null){

                _usuario.value =
                    resultado

            }
            else{

                _mensaje.value =
                    "No se pudo cargar el perfil"

            }

        }

    }

    // CERRAR SESIÓN
    fun cerrarSesion(
        irLogin: () -> Unit
    ){
        repository.cerrarSesion()

        _usuario.value =
            null

        irLogin()

    }

}