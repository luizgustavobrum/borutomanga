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
        MutableStateFlow(UiStateManga.Initial)
    val uiStateManga: StateFlow<UiStateManga> = _uiStateManga

    init {
        onEventCoroutine()
    }

    private fun onEventCoroutine() {
        _uiStateManga.value = UiStateManga.Loading
        viewModelScope.launch {
            mangaUseCase.getMangaList().collect {
                _uiStateManga.value = UiStateManga.Success(it)
            }
        }
    }
}
