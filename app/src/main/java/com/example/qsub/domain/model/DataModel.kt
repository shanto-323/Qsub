package com.example.qsub.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

data class DataModel(
  val id: String = "",
  val name: String = "",

//  SET BY User
  val query: String = "",
  val created: Long = System.currentTimeMillis(),
  val updated: Boolean = false
)
