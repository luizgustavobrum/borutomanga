package com.app.boruto.manga.presentation

import androidx.lifecycle.*
import com.app.boruto.manga.domain.usecase.MangaUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class MangaViewModel(
    private val mangaUseCase: MangaUseCase
) : ViewModel() {

    private val _uiStateManga: MutableStateFlow<UiStateManga> =
        MutableStateFlow(UiStateManga.Loading)
    val uiStateManga: StateFlow<UiStateManga> = _uiStateManga

    init {
        getListMangaEvent()
    }

    private fun getListMangaEvent() {
        viewModelScope.launch {
            mangaUseCase.getMangaList().collect {
                _uiStateManga.value = UiStateManga.Success(it)
            }
        }
    }
}
