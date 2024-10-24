package com.example.qsub.data.remote.repository

import com.example.qsub.domain.model.DataModel
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.FireStoreRepository
import com.example.qsub.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
  private val db: FirebaseFirestore
) : FireStoreRepository {
  override suspend fun putData(model: DataModel): Response<Boolean> {
    return try {
      db.collection(Constants.DATA_COLLECTION).add(model).await()
      Response.Success(true)
    } catch (e: Exception) {
      Response.Error(e)
    }
  }

  override suspend fun deleteData(id: String): Response<Boolean> {
    TODO("Not yet implemented")
  }

  override suspend fun updateData(model: DataModel): Response<Boolean> {
    TODO("Not yet implemented")
  }

  override fun getData(id: String): Flow<List<DataModel>> = callbackFlow {
    val list = mutableListOf<DataModel>()
    db.collection(Constants.DATA_COLLECTION)
      .whereEqualTo("name", id)
      .get()
      .addOnSuccessListener { documents ->
        for (document in documents) {
          val data = document?.data
          val model = DataModel(
            id = data?.get("id").toString(),
            name = data?.get("name").toString(),
            query = data?.get("query").toString(),
            updated = (data?.get("updated") as? Boolean) ?: false,
          )
          list.add(model)
        }
        trySend(list)
      }
    awaitClose()
  }

  override fun getAllUserData(): Flow<List<DataModel>> = callbackFlow {
    val list = mutableListOf<DataModel>()
    db.collection(Constants.DATA_COLLECTION)
      .get()
      .addOnSuccessListener { documents ->
        for (document in documents) {
          val data = document?.data
          val model = DataModel(
            id = data?.get("id").toString(),
            name = data?.get("name").toString(),
            query = data?.get("query").toString(),
            updated = (data?.get("updated") as? Boolean) ?: false,
            created = (data?.get("created") as? Long) ?: System.currentTimeMillis(),
          )
          list.add(model)
        }
        trySend(list)
      }
    awaitClose()
  }
}