package com.example.qsub.di

import com.example.qsub.data.remote.repository.AuthRepositoryImpl
import com.example.qsub.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
    auth = FirebaseAuth.getInstance()
  )

}