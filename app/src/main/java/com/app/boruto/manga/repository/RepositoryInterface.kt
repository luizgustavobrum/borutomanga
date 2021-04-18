package com.app.boruto.manga.repository

import com.app.boruto.manga.model.Manga

interface RepositoryInterface {
    fun onMangaListerner(onSuccess: (MutableList<Manga>) -> Unit, onError: (String) -> Unit)
    suspend fun onMangaCoroutine(): Result<List<Manga>>
}