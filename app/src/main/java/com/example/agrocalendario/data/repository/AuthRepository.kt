package com.example.agrocalendario.data.repository

import android.content.Context
import com.example.agrocalendario.data.model.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    private val firestore = FirebaseFirestore.getInstance()


    // REGISTRO CON CORREO

    suspend fun registrarUsuario(
        nombre: String,
        apellido: String,
        correo: String,
        clave: String
    ): Result<String> {

        return try {

            val resultado =
                auth.createUserWithEmailAndPassword(
                    correo,
                    clave
                ).await()

            val uid =
                resultado.user?.uid ?: ""

            val usuario = Usuario(

                id = uid,

                nombre = nombre,

                apellido = apellido,

                correo = correo

            )

            firestore
                .collection("usuarios")
                .document(uid)
                .set(usuario)
                .await()

            Result.success(uid)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    // LOGIN CORREO

    suspend fun iniciarSesion(
        correo: String,
        contraseña: String
    ): Result<String> {

        return try {

            val resultado =
                auth.signInWithEmailAndPassword(
                    correo,
                    contraseña
                ).await()

            Result.success(
                resultado.user?.uid ?: ""
            )

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    // GOOGLE LOGIN

    fun obtenerClienteGoogle(
        context: Context
    ): GoogleSignInClient {

        val opciones =
            GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )

                .requestIdToken(

                    "6065117195-o0rctgpd2ph5h2m19ppauam33t3g8lc6.apps.googleusercontent.com"

                )
                .requestEmail()

                .build()

        return GoogleSignIn.getClient(
            context,
            opciones
        )

    }

    suspend fun iniciarSesionGoogle(
        idToken: String
    ): Result<String> {

        return try {

            val credencial =
                GoogleAuthProvider
                    .getCredential(
                        idToken,
                        null
                    )

            val resultado =
                auth
                    .signInWithCredential(
                        credencial
                    )
                    .await()

            val usuarioFirebase =
                auth.currentUser

            val uid =
                usuarioFirebase?.uid ?: ""

            if(usuarioFirebase != null){

                val usuario = Usuario(

                    id = uid,

                    nombre =
                        usuarioFirebase?.displayName ?: "",

                    apellido = "",

                    correo =
                        auth.currentUser?.email ?: "",

                    foto =
                        auth.currentUser?.photoUrl?.toString() ?: ""

                )

                firestore
                    .collection("usuarios")
                    .document(uid)
                    .set(usuario)
                    .await()


            }

            Result.success(uid)

        }catch(e:Exception){

            Result.failure(e)

        }

    }

    // OBTENER PERFIL

    suspend fun obtenerPerfil(): Usuario? {

        return try {

            val uid =
                auth.currentUser?.uid
                    ?: return null

            firestore
                .collection("usuarios")
                .document(uid)
                .get()
                .await()
                .toObject(
                    Usuario::class.java
                )

        } catch(e:Exception){

            null

        }

    }

    // CERRAR SESIÓN

    fun cerrarSesion(){

        auth.signOut()

    }

    // USUARIO ACTUAL

    fun usuarioActual(): String? {

        return auth.currentUser?.uid

    }

}