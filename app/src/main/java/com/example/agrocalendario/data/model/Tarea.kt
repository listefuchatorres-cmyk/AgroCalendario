package com.example.agrocalendario.data.model


data class Tarea(

    val id: String = "",

    val usuarioId: String = "",

    val titulo: String = "",

    val descripcion: String = "",

    val fecha: String = "",

    val recordatorio: Boolean = false,

    val repeticion: String = "",

    val completada: Boolean = false

)