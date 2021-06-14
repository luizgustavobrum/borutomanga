package com.app.boruto.manga.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.boruto.manga.model.Manga
import com.app.boruto.manga.repository.RepositoryInterface
import kotlinx.coroutines.*

class MangaViewModel(
    private val repository: RepositoryInterface
) : ViewModel() {

    companion object {
        private val TAG = MangaViewModel::class.java.simpleName
    }

    private val _mangaLiveData = MutableLiveData<List<Manga>>()
    val mangaLiveData: LiveData<List<Manga>> = _mangaLiveData

    init {
        onEventCoroutine()
    }

    private fun onEventCoroutine(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch {
            val response = withContext(dispatcher) {
                repository.onMangaCoroutine()
            }
            response.fold(
                onSuccess = {
                    _mangaLiveData.postValue(it)
                }, onFailure = {
                    Log.e(TAG, "Erro: ${it.message.orEmpty()}")
                })
        }
    }
}
