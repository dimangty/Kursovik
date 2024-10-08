package com.example.kursovik.UI.Profile

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursovik.Core.Domain.Repository.ProfileRepositoryImpl
import com.example.kursovik.Core.Models.Poso.User
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.MainActivity
import com.example.kursovik.Network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileService: ProfileRepositoryImpl,
    private val progresService: ProgresService,
    private val errorService: ErrorService
) : ViewModel() {

    // Some code
    private val mUser = MutableLiveData<User?>()
    val user = mUser

    private var loadingSuggestionsTask: Job? = null

    fun loadUser() {

        loadingSuggestionsTask?.cancel()
        progresService.show.value = true
        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { profileService.getUser() }
                    .takeIf { it.isSuccess }
                    ?.let {
                        progresService.hideLoading()
                        val obj = it.getOrNull()?.users?.first()
                        if (obj != null) {
                            mUser.value = User().initFrom(obj)
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
