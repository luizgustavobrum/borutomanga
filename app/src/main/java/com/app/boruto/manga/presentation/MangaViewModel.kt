package com.app.boruto.manga.presentation

import androidx.lifecycle.*
import com.app.boruto.manga.model.Manga
import com.app.boruto.manga.repository.FirebaseRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MangaViewModel(
    private val repository: FirebaseRepository
) : ViewModel() {

    private val _mangaLiveData = MutableLiveData<List<Manga>>()
    val mangaLiveData: LiveData<List<Manga>> = _mangaLiveData

    init {
        onEventCoroutine()
    }

    private fun onEventCoroutine() {
        viewModelScope.launch {
            repository.onMangaCoroutine().collect {
                _mangaLiveData.postValue(it)
            }
        }
    }
}
