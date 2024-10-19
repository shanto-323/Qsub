package com.example.qsub.presentation.auth.state

sealed class UiEvent {
  data class EmailChanged(val email: String) : UiEvent()
  data class PasswordChanged(val password: String) : UiEvent()
  data object EnterClicked : UiEvent()
}