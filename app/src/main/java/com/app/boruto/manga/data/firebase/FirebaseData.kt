package com.app.boruto.manga.data.firebase

import com.app.boruto.manga.data.model.MangaResponse
import kotlinx.coroutines.flow.Flow

interface FirebaseData {
    fun getMangaList(): Flow<List<MangaResponse>>
}