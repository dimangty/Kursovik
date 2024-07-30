package com.example.kursovik.UI.Friends.FriendsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursovik.Core.Domain.Repository.FriendsRepositoryImpl
import com.example.kursovik.Core.Domain.Repository.NewsRepositoryImpl
import com.example.kursovik.Core.Models.Poso.Friend
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.Core.Utils.ViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsService: FriendsRepositoryImpl,
    private val progresService: ProgresService,
    private val errorService: ErrorService
): ViewModel() {
    private var loadingSuggestionsTask: Job? = null
    private val mFriends = MutableLiveData<List<Friend>>()
    val friends = mFriends
    fun loadFriends() {
        loadingSuggestionsTask?.cancel()
        progresService.show.value = true
        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { friendsService.getFriends() }
                    .takeIf { it.isSuccess }
                    ?.let {
                        progresService.show.value = false
                        val response = it.getOrNull()
                        if (response != null) {
                            mFriends.value = ViewModelFactory().getFriends(response)
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