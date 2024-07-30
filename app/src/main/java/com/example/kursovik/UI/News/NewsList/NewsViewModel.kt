package com.example.kursovik.UI.News

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursovik.Core.Domain.Repository.NewsRepositoryImpl
import com.example.kursovik.Core.Domain.Repository.ProfileRepositoryImpl
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Models.Poso.User
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.Core.Utils.ViewModelFactory
import com.example.kursovik.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsService: NewsRepositoryImpl,
    private val progresService: ProgresService,
    private val errorService: ErrorService
): ViewModel() {

    private var loadingSuggestionsTask: Job? = null
    private val mNews = MutableLiveData<List<Post>>()
    val news = mNews
    fun loadNews() {
        loadingSuggestionsTask?.cancel()
        progresService.show.value = true
        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { newsService.getNews() }
                    .takeIf { it.isSuccess }
                    ?.let {
                        progresService.show.value = false
                        val response = it.getOrNull()?.response
                        if (response != null) {
                            val news = ViewModelFactory().getPosts(response)
                            mNews.value = news
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