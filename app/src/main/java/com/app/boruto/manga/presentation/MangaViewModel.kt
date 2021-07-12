package com.app.boruto.manga.presentation

import androidx.lifecycle.*
import com.app.boruto.manga.repository.FirebaseRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class MangaViewModel(
    private val repository: FirebaseRepository
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
            repository.onMangaCoroutine().collect {
                _uiStateManga.value = UiStateManga.Success(it)
            }
        }
    }
}
