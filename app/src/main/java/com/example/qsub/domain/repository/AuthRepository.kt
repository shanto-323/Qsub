package com.example.qsub.domain.repository

import com.example.qsub.domain.model.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias InResponse = Response<Boolean>

interface AuthRepository {

  val currentUser : FirebaseUser?

  suspend fun login(email: String, password: String): InResponse

  suspend fun signup(email: String, password: String): InResponse

  fun logout()

  suspend fun reloadUser():InResponse

 fun getAuthState(viewmodelScope: CoroutineScope): StateFlow<Boolean>
}