package com.app.boruto.manga.repository

import com.app.boruto.manga.model.Manga

interface FirebaseRepository {
    suspend fun onMangaCoroutine(): Result<List<Manga>>
}