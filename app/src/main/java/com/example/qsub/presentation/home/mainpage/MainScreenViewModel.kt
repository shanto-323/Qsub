package com.example.qsub.presentation.home.mainpage

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
  private val repo: AuthRepository
) : ViewModel() {
  var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
    private set

  fun signOut() {
    repo.logout()
    viewModelScope.launch {
      response = Response.Loading
      response = repo.reloadUser()
    }
  }
}