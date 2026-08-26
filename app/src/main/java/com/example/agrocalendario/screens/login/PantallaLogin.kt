package com.example.agrocalendario.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrocalendario.R
import com.example.agrocalendario.ui.theme.Blanco
import com.example.agrocalendario.ui.theme.VerdeAgro
import com.example.agrocalendario.viewmodel.AuthViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions


private val BlancoTransparente = Blanco.copy(alpha = 0.92f)

@Composable
fun PantallaLogin(
    irRegistro: () -> Unit,
    irPrincipal: () -> Unit,
    viewModel: AuthViewModel,
    iniciarGoogle: () -> Unit
) {

    val contexto = LocalContext.current

    var correo by remember {
        mutableStateOf("")
    }

    var contraseña by remember {
        mutableStateOf("")
    }

    var mostrarClave by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.fondo_agricultura
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(BlancoTransparente)
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.spacedBy(18.dp)

            ) {

                Text(
                    text = "Iniciar sesión",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = VerdeAgro,
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(

                    value = correo,

                    onValueChange = {
                        correo = it
                    },

                    label = {
                        Text("Correo")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    keyboardOptions = KeyboardOptions(

                        keyboardType = KeyboardType.Email

                    ),

                    shape = RoundedCornerShape(12.dp)

                )

                OutlinedTextField(

                    value = contraseña,

                    onValueChange = {

                        contraseña = it

                    },

                    label = {

                        Text("Contraseña")

                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(12.dp),


                    visualTransformation =

                        if (mostrarClave)

                            androidx.compose.ui.text.input.VisualTransformation.None

                        else

                            PasswordVisualTransformation(),


                    trailingIcon = {


                        IconButton(

                            onClick = {

                                mostrarClave = !mostrarClave

                            }

                        ) {


                            Icon(

                                imageVector =

                                    if (mostrarClave)

                                        Icons.Default.Visibility

                                    else

                                        Icons.Default.VisibilityOff,


                                contentDescription =

                                    if (mostrarClave)

                                        "Ocultar contraseña"

                                    else

                                        "Mostrar contraseña"

                            )

                        }


                    }

                )

                // LOGIN CON CORREO

                Button(
                    onClick = {

                        if (
                            correo.isBlank() ||
                            contraseña.isBlank()
                        ) {

                            Toast.makeText(
                                contexto,
                                "Completa todos los campos",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        viewModel.iniciarSesion(

                            correo = correo,

                            contraseña = contraseña,

                            onSuccess = {

                                Toast.makeText(
                                    contexto,
                                    "✅ Bienvenido",
                                    Toast.LENGTH_SHORT
                                ).show()

                                irPrincipal()

                            },

                            onError = { mensaje ->

                                Toast.makeText(
                                    contexto,
                                    "❌ $mensaje",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                        )

                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeAgro
                    ),

                    shape = RoundedCornerShape(12.dp)

                ) {

                    Text(
                        text = "Iniciar sesión",
                        color = Color.White
                    )

                }

                // LOGIN GOOGLE

                Button(
                    onClick = {

                        iniciarGoogle()

                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeAgro
                    ),

                    shape = RoundedCornerShape(12.dp)

                ) {

                    Text(
                        text = "Continuar con Google",
                        color = Color.White
                    )

                }

                // REGISTRO

                Button(
                    onClick = irRegistro,

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeAgro
                    ),

                    shape = RoundedCornerShape(12.dp)

                ) {

                    Text(
                        text = "Crear una cuenta",
                        color = Color.White
                    )

                }

            }

        }

    }

}