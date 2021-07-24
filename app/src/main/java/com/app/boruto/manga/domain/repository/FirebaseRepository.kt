package com.app.boruto.manga.domain.repository

import com.app.boruto.manga.domain.model.Manga
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun getMangaList(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<List<Manga>>
}