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

    private val TAG = MangaViewModel::class.java.simpleName
    private val _mangaLiveData = MutableLiveData<List<Manga>>()
    val mangaLiveData: LiveData<List<Manga>> = _mangaLiveData

    init {
        onEventCoroutine()
    }

    private fun onEventListener() {
        repository.onMangaListerner(
            onSuccess = { list ->
                Log.d(TAG, list.toString())
                _mangaLiveData.postValue(list)
            },
            onError = { error ->
                Log.e(TAG, "Error: $error")
            })
    }

    private fun onEventCoroutine() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.onMangaCoroutine()
            if (response.isSuccess) {
                response.fold(
                    onSuccess = {
                        _mangaLiveData.postValue(it)
                    }, onFailure = {
                        Log.e(TAG, "Erro: ${it.message.orEmpty()}")
                    })
            } else {
                Log.e(TAG, "Não foi possivel fazer o request dos dados...")
            }
        }

    }

}
