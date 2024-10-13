package com.example.qsub.domain.model

import kotlin.Exception

sealed class Response<out T> {

  data object Loading : Response<Nothing>()

  data class Success<out T>(
    val data: T
  ) : Response<T>()

  data class Error(
    val err: Exception
  ) : Response<Nothing>()

}