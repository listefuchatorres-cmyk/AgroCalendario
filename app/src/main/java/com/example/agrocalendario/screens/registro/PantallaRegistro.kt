package com.example.agrocalendario.screens.registro

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrocalendario.R
import com.example.agrocalendario.ui.theme.Blanco
import com.example.agrocalendario.ui.theme.VerdeAgro
import com.example.agrocalendario.viewmodel.AuthViewModel

private val BlancoTransparente = Blanco.copy(alpha = 0.92f)

@Composable
fun PantallaRegistro(

    irLogin: () -> Unit,

    irPrincipal: () -> Unit,

    viewModel: AuthViewModel = viewModel()

) {

    val contexto = LocalContext.current

    var nombre by remember { mutableStateOf("") }

    var apellido by remember { mutableStateOf("") }

    var correo by remember { mutableStateOf("") }

    var clave by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.fondo_agricultura),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

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

                    text = "Crear cuenta",

                    fontSize = 30.sp,

                    fontWeight = FontWeight.ExtraBold,

                    color = VerdeAgro,

                    textAlign = TextAlign.Center

                )

                OutlinedTextField(

                    value = nombre,

                    onValueChange = {

                        nombre = it

                    },

                    label = {

                        Text("Nombre")

                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(12.dp),

                    singleLine = true,

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = Color.White,

                        unfocusedContainerColor = Color.White,

                        focusedBorderColor = VerdeAgro,

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = VerdeAgro

                    )

                )

                OutlinedTextField(

                    value = apellido,

                    onValueChange = {

                        apellido = it

                    },

                    label = {

                        Text("Apellido")

                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(12.dp),

                    singleLine = true,

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = Color.White,

                        unfocusedContainerColor = Color.White,

                        focusedBorderColor = VerdeAgro,

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = VerdeAgro

                    )

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

                    shape = RoundedCornerShape(12.dp),

                    singleLine = true,

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = Color.White,

                        unfocusedContainerColor = Color.White,

                        focusedBorderColor = VerdeAgro,

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = VerdeAgro

                    )

                )

                OutlinedTextField(

                    value = clave,

                    onValueChange = {

                        clave = it

                    },

                    label = {

                        Text("Contraseña")

                    },

                    visualTransformation = PasswordVisualTransformation(),

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(12.dp),

                    singleLine = true,

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = Color.White,

                        unfocusedContainerColor = Color.White,

                        focusedBorderColor = VerdeAgro,

                        unfocusedBorderColor = Color.Gray,

                        focusedLabelColor = VerdeAgro

                    )

                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(

                    onClick = {

                        if (

                            nombre.isBlank() ||

                            apellido.isBlank() ||

                            correo.isBlank() ||

                            clave.isBlank()

                        ) {

                            Toast.makeText(

                                contexto,

                                "Completa todos los campos",

                                Toast.LENGTH_SHORT

                            ).show()

                            return@Button

                        }

                        if (clave.length < 6) {

                            Toast.makeText(

                                contexto,

                                "La contraseña debe tener al menos 6 caracteres",

                                Toast.LENGTH_SHORT

                            ).show()

                            return@Button

                        }

                        viewModel.registrar(

                            nombre = nombre,

                            apellido = apellido,

                            correo = correo,

                            clave = clave,

                            onSuccess = {

                                Toast.makeText(

                                    contexto,

                                    "✅ Registro exitoso",

                                    Toast.LENGTH_SHORT

                                ).show()

                                irPrincipal()

                            },

                            onError = { mensaje ->

                                Toast.makeText(

                                    contexto,

                                    mensaje,

                                    Toast.LENGTH_LONG

                                ).show()

                            }

                        )

                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = VerdeAgro

                    ),

                    shape = RoundedCornerShape(12.dp)

                ) {

                    Text(

                        text = "Registrarse",

                        color = Color.White,

                        fontSize = 17.sp,

                        fontWeight = FontWeight.Bold

                    )

                }

                Button(

                    onClick = irLogin,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),

                    colors = ButtonDefaults.buttonColors(

                        containerColor = VerdeAgro

                    ),

                    shape = RoundedCornerShape(12.dp)

                ) {

                    Text(

                        text = "Ya tengo una cuenta",

                        color = Color.White,

                        fontSize = 17.sp,

                        fontWeight = FontWeight.Bold

                    )

                }

            }

        }

    }

}