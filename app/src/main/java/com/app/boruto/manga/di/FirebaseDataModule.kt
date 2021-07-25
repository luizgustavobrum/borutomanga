package com.app.boruto.manga.di

import com.app.boruto.manga.data.firebase.FirebaseData
import com.app.boruto.manga.data.firebase.FirebaseDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseDataModule {

    @Binds
    abstract fun bindFirebaseData(
        firebaseDataImpl: FirebaseDataImpl
    ): FirebaseData

}