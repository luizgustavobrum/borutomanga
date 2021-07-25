package com.app.boruto.manga.di

import com.app.boruto.manga.data.repository.FirebaseRepositoryImpl
import com.app.boruto.manga.domain.repository.FirebaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseRepositoryModule {
    @Binds
    abstract fun bindFirebaseRepository(
        firebaseRepositoryImpl: FirebaseRepositoryImpl
    ): FirebaseRepository
}