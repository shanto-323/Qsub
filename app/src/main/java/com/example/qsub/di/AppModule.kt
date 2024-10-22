package com.example.qsub.di

import com.example.qsub.data.remote.repository.AuthRepositoryImpl
import com.example.qsub.data.remote.repository.FireStoreRepositoryImpl
import com.example.qsub.domain.repository.AuthRepository
import com.example.qsub.domain.repository.FireStoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
    auth = FirebaseAuth.getInstance(),
    db = Firebase.firestore
  )

  @Provides
  fun provideFireStoreRepository(): FireStoreRepository = FireStoreRepositoryImpl(
    db = Firebase.firestore
  )

}