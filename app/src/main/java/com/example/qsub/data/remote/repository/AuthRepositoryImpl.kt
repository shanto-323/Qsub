package com.example.qsub.data.remote.repository

import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.AuthRepository
import com.example.qsub.domain.repository.InResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

class AuthRepositoryImpl @Inject constructor(
  private val auth: FirebaseAuth
) : AuthRepository {
  override val currentUser get() = auth.currentUser

  override suspend fun login(
    email: String, password: String
  ): InResponse = try {
    auth.signInWithEmailAndPassword(email, password)
    Response.Success(true)
  } catch (e: Exception) {
    Response.Error(e)
  }

  override suspend fun signup(
    email: String, password: String
  ): InResponse = try {
    auth.createUserWithEmailAndPassword(email, password)
    Response.Success(true)
  } catch (e: Exception) {
    Response.Error(e)
  }

  override fun logout() = auth.signOut()

  override suspend fun reloadUser(): InResponse =
    try {
      auth.currentUser?.reload()?.await()
      Response.Success(true)
    } catch (e: Exception) {
      Response.Error(e)
    }

  override fun getAuthState(viewmodelScope: CoroutineScope) = callbackFlow {
    val authListener = FirebaseAuth.AuthStateListener { auth ->
      trySend(auth.currentUser == null)
    }
    auth.addAuthStateListener(authListener)
    awaitClose {
      auth.removeAuthStateListener(authListener)
    }
  }.stateIn(viewmodelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)
}