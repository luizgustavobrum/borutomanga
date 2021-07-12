package com.app.boruto.manga.repository

import com.app.boruto.manga.data.FirebaseData
import com.app.boruto.manga.mapper.toList
import com.app.boruto.manga.model.Manga
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FirebaseRepositoryImpl(
    private val data: FirebaseData
) : FirebaseRepository {

    override suspend fun onMangaCoroutine(
        dispatcher: CoroutineDispatcher
    ): Flow<List<Manga>> = data.getMangaList()
        .map {
            it.toList()
        }.flowOn(dispatcher)

}