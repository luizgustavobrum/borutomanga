package com.app.boruto.manga.presentation

import androidx.lifecycle.*
import com.app.boruto.manga.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
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
