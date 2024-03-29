package com.app.boruto.manga.data.repository

import com.app.boruto.manga.data.firebase.FirebaseData
import com.app.boruto.manga.data.mapper.toMangaModel
import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.domain.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val data: FirebaseData
) : FirebaseRepository {

    override suspend fun getMangaList(
        dispatcher: CoroutineDispatcher
    ): Flow<List<Manga>> = data.getMangaList()
        .map { listMangaResponse ->
            listMangaResponse.map {
                it.toMangaModel()
            }
        }.flowOn(dispatcher)
}