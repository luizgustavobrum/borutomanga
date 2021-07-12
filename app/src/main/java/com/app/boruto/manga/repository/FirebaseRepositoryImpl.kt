package com.app.boruto.manga.repository

import com.app.boruto.manga.data.FirebaseData
import com.app.boruto.manga.data.MangaResponse
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


    fun List<MangaResponse>.toList(): List<Manga> {
        val listManga: MutableList<Manga> = mutableListOf()
        this.forEach {
            listManga.add(
                Manga(
                    title = it.title,
                    numero = it.numero,
                    image = it.image,
                    link = it.link
                )
            )
        }
        return listManga
    }
}