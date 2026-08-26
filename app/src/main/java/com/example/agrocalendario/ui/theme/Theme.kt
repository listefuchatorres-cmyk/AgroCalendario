package com.example.agrocalendario.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.agrocalendario.data.preferences.PreferenciasDataStore
import androidx.compose.runtime.collectAsState

private val LightColorScheme = lightColorScheme(

    primary = VerdeAgro,

    secondary = VerdeClaroAgro,

    tertiary = MoradoEditar,

    background = FondoAgro,

    surface = Blanco,

    onPrimary = Blanco,

    onSecondary = Blanco,

    onTertiary = Blanco,

    onBackground = GrisTexto,

    onSurface = GrisTexto
)

private val DarkColorScheme = darkColorScheme(

    primary = VerdeAgro,

    secondary = VerdeClaroAgro,

    tertiary = MoradoEditar,

    background = androidx.compose.ui.graphics.Color(0xFF121212),

    surface = androidx.compose.ui.graphics.Color(0xFF1E1E1E),

    onBackground = androidx.compose.ui.graphics.Color.White,

    onSurface = androidx.compose.ui.graphics.Color.White
)

@Composable
fun AgroCalendarioTheme(

    content: @Composable () -> Unit

) {

    val context = LocalContext.current

    // Leer tema guardado en DataStore

    val temaGuardado by
    PreferenciasDataStore
        .tema(context)
        .collectAsState(initial = "sistema")


    // Determinar si debe utilizar modo oscuro

    val darkTheme = when (temaGuardado) {

        "oscuro" -> true

        "claro" -> false

        else -> isSystemInDarkTheme()
    }


    MaterialTheme(

        colorScheme =
            if (darkTheme)
                DarkColorScheme
            else
                LightColorScheme,

        typography = Typography,

        content = content

    )
}