package com.app.boruto.manga.repository

import com.app.boruto.manga.model.Manga
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun onMangaCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<List<Manga>>
}