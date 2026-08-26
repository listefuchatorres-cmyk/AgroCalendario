package com.example.agrocalendario.data.repository

import com.example.agrocalendario.data.model.Actividad
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ActividadRepository {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    // ==========================
    // GUARDAR
    // ==========================

    suspend fun guardarActividad(
        actividad: Actividad
    ): Result<Actividad> {

        return try {

            val uid = auth.currentUser?.uid
                ?: return Result.failure(
                    Exception("Usuario no autenticado")
                )

            val documento =
                db.collection("usuarios")
                    .document(uid)
                    .collection("actividades")
                    .document()

            val nuevaActividad =
                actividad.copy(
                    id = documento.id,
                    uidUsuario = uid
                )

            documento
                .set(nuevaActividad)
                .await()

            Result.success(
                nuevaActividad
            )

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    // ==========================
    // ESCUCHAR ACTIVIDADES
    // ==========================

    fun escucharActividades(): Flow<List<Actividad>> =
        callbackFlow {

            val uid =
                auth.currentUser?.uid

            if (uid == null) {

                trySend(emptyList())

                close()

                return@callbackFlow

            }

            val listener: ListenerRegistration =

                db.collection("usuarios")
                    .document(uid)
                    .collection("actividades")
                    .addSnapshotListener { value, error ->

                        if (error != null)
                            return@addSnapshotListener

                        val lista =
                            value?.toObjects(
                                Actividad::class.java
                            ) ?: emptyList()

                        trySend(lista)

                    }

            awaitClose {

                listener.remove()

            }

        }

    // ==========================
    // ACTUALIZAR
    // ==========================

    suspend fun actualizarActividad(
        actividad: Actividad
    ): Result<Unit> {

        return try {

            val uid =
                auth.currentUser?.uid
                    ?: return Result.failure(
                        Exception(
                            "Usuario no autenticado"
                        )
                    )

            db.collection("usuarios")
                .document(uid)
                .collection("actividades")
                .document(actividad.id)
                .set(actividad)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================

    suspend fun eliminarActividad(
        id: String
    ): Result<Unit> {

        return try {

            val uid =
                auth.currentUser?.uid
                    ?: return Result.failure(
                        Exception(
                            "Usuario no autenticado"
                        )
                    )

            db.collection("usuarios")
                .document(uid)
                .collection("actividades")
                .document(id)
                .delete()
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}