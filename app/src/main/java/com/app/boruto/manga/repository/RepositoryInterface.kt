package com.app.boruto.manga.repository

import com.app.boruto.manga.model.Manga

interface RepositoryInterface {
    suspend fun onMangaCoroutine(): Result<List<Manga>>
}