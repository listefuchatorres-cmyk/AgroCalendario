package com.example.agrocalendario.data.model


data class Actividad(

    val id: String = "",

    val uidUsuario: String = "",

    val actividad: String = "",

    val descripcion: String = "",

    val fecha: String = "",

    // Activa o desactiva el recordatorio
    val recordatorio: Boolean = false,

    // Indica que la notificación será un día antes
    val avisoPrevio: Boolean = true,

    val repeticion: String = "No repetir",

    val diasPersonalizados: Int = 0,

    // Próxima fecha cuando existe repetición
    val proximaFecha: String = "",

    val fechaCreacion: Long = System.currentTimeMillis(),

    // Estado de la actividad
    val realizada: Boolean = false

)