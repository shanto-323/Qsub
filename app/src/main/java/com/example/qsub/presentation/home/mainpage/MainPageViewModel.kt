package com.example.qsub.presentation.home.mainpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qsub.domain.model.DataModel
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.AuthRepository
import com.example.qsub.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
  private val repo: AuthRepository,
  private val fireStoreRepo: FireStoreRepository
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

  fun putData() {

  }
}