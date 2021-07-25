package com.app.boruto.manga.di

import com.app.boruto.manga.domain.usecase.MangaUseCase
import com.app.boruto.manga.domain.usecase.MangaUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MangaUseCaseModule {

    @Binds
    abstract fun bindMangaUseCasa(
        mangaUseCaseImpl: MangaUseCaseImpl
    ): MangaUseCase
}