package com.example.qsub.domain.model

data class DataModel(
  val id: String = "",
  val name: String = "",

//  SET BY User
  val query: String = "",
  val updated: Boolean = false
)
