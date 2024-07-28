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
        progresService.showLoadingDialog()
        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { newsService.getNews(MainActivity.token) }
                    .takeIf { it.isSuccess }
                    ?.let {
                        progresService.hideLoading()
                        val obj = it.getOrNull()?.response
                        if (obj != null) {
                            val news = ViewModelFactory().getPosts(obj)
                            mNews.value = news
                        }
                    } ?: let {
                    progresService.hideLoading()
                    errorService.show("Ошибка загрузки")
                }
            } catch (t: Throwable) {
                progresService.hideLoading()
            }
        }
    }

    fun setContext(context: Context?) {
        errorService.setContext(context)
        progresService.setContext(context)
    }
}