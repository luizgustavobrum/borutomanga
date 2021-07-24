package com.app.boruto.manga.data.repository

import com.app.boruto.manga.data.firebase.FirebaseData
import com.app.boruto.manga.data.mapper.toList
import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.domain.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FirebaseRepositoryImpl(
    private val data: FirebaseData
) : FirebaseRepository {

    override suspend fun getMangaList(
        dispatcher: CoroutineDispatcher
    ): Flow<List<Manga>> = data.getMangaList()
        .map {
            it.toList()
        }.flowOn(dispatcher)
}