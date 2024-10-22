package com.example.qsub.data.remote.repository

import com.example.qsub.domain.model.DataModel
import com.example.qsub.domain.model.Response
import com.example.qsub.domain.repository.FireStoreRepository
import com.example.qsub.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
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

  override fun getData(id: String): Flow<List<DataModel>> {
    TODO("Not yet implemented")
  }
}