package com.app.boruto.manga.presentation

import com.app.boruto.manga.domain.model.Manga

sealed class UiStateManga {
    object Initial : UiStateManga()
    object Loading : UiStateManga()
    data class Success(val value: List<Manga>) : UiStateManga()
    object Error : UiStateManga()
}
