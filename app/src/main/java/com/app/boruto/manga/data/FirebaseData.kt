package com.app.boruto.manga.data

import kotlinx.coroutines.flow.Flow

interface FirebaseData {
    fun getMangaList(): Flow<List<MangaResponse>>
}