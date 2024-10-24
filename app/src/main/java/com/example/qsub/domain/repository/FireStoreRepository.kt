package com.example.qsub.domain.repository

import com.example.qsub.domain.model.DataModel
import com.example.qsub.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FireStoreRepository {

  suspend fun putData(model: DataModel): Response<Boolean>

  suspend fun deleteData(id:String) : Response<Boolean>

  suspend fun updateData(model: DataModel) : Response<Boolean>

  fun getData(id:String) : Flow<List<DataModel>>

  fun getAllUserData() : Flow<List<DataModel>>

}