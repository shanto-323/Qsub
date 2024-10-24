package com.example.qsub.presentation.home.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qsub.domain.model.DataModel
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.AuthRepository
import com.example.qsub.domain.repository.FireStoreRepository
import com.example.qsub.presentation.home.create.items.CreateEvent
import com.example.qsub.presentation.home.create.items.CreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateScreenViewModel @Inject constructor(
  private val authRepo: AuthRepository,
  private val repo: FireStoreRepository
) : ViewModel() {
  var response by mutableStateOf<Response<Boolean>>(Response.Success(false))
    private set
  var state by mutableStateOf(CreateState())
    private set

  fun onResponse(event: CreateEvent) {
    when (event) {
      is CreateEvent.Query -> {
        state = state.copy(query = event.query)
      }

      CreateEvent.Submit -> putData(query = state.query)
    }
  }

  private fun putData(query: String = "NoQuery") {
    val userId = authRepo?.currentUser
    val model = DataModel(
      id = userId?.uid.toString(),
      name = userId?.email.toString().take(4),
      query = query,
      updated = false
    )
    viewModelScope.launch {
      repo.putData(model)
    }
  }
}