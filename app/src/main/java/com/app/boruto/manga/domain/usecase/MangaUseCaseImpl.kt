package com.app.boruto.manga.domain.usecase

import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository
) : MangaUseCase {
    override suspend fun getMangaList(): Flow<List<Manga>> {
        return repository.getMangaList()
    }
}