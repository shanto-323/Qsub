package com.example.qsub.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.AuthRepository
import com.example.qsub.presentation.auth.state.UiEvent
import com.example.qsub.presentation.auth.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginUiViewModel @Inject constructor(
  private val repo: AuthRepository
) : ViewModel() {
  private var state by mutableStateOf(UiState())
  var signInResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
    private set

  fun onEvent(event: UiEvent) {
    when (event) {
      is UiEvent.EmailChanged -> {
        state = state.copy(
          email = event.email
        )
      }

      is UiEvent.PasswordChanged -> {
        state = state.copy(
          password = event.password
        )
      }

      UiEvent.EnterClicked -> {
        login(state.email + "@gmail.com", state.password)
      }
    }
  }

  private fun login(email: String, password: String) {
    viewModelScope.launch {
      signInResponse = Response.Loading
      signInResponse = repo.login(email, password)
    }
  }
}