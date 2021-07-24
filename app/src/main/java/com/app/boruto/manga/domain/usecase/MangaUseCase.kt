package com.app.boruto.manga.domain.usecase

import com.app.boruto.manga.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaUseCase {
   suspend fun getMangaList(): Flow<List<Manga>>
}