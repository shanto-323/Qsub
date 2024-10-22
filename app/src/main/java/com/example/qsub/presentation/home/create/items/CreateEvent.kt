package com.example.qsub.presentation.home.create.items

sealed class CreateEvent {
  data class Query(val query: String) : CreateEvent()
  data object Submit : CreateEvent()
}