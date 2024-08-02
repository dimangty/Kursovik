package com.example.kursovik.UI.Photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursovik.Core.Domain.Repository.PhotoRepositoryImpl
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.Core.Utils.ViewModelFactory
import com.example.kursovik.Network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photoService: PhotoRepositoryImpl,
                                          private val progresService: ProgresService,
                                          private val errorService: ErrorService): ViewModel() {
    private var loadingSuggestionsTask: Job? = null

    private val mPhotos = MutableLiveData<List<String>>()
    val photos = mPhotos
    fun loadPhotos() {
        loadingSuggestionsTask?.cancel()
        progresService.show.value = true
        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { photoService.getPhotos() }
                    .takeIf { it.isSuccess }
                    ?.let {
                        progresService.show.value = false
                        val response = it.getOrNull()?.response
                        if (response != null) {
                            mPhotos.value = ViewModelFactory().getPhotos(response = response)
                        }
                    } ?: let {
                    progresService.show.value = false
                    errorService.show.value = "Ошибка загрузки"
                }
            } catch (t: Throwable) {
                progresService.show.value = false
            }
        }
    }
}