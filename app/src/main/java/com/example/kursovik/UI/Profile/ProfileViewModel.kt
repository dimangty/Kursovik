package com.example.kursovik.UI.Profile

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursovik.Core.Domain.Repository.ProfileRepositoryImpl
import com.example.kursovik.Core.Models.Poso.User
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
    private val profileService: ProfileRepositoryImpl
) : ViewModel() {

    // Some code
    private val mUser = MutableLiveData<User?>()
    val user = mUser

    private var loadingSuggestionsTask: Job? = null

    fun loadUser() {

        loadingSuggestionsTask?.cancel()

        loadingSuggestionsTask = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { profileService.getUser(MainActivity.token) }
                    .takeIf { it.isSuccess }
                    ?.let {
                        val obj = it.getOrNull()?.users?.first()
                        if (obj != null) {
                            mUser.value = User(obj.firstName, obj.lastName)
                        }

                    } ?: let {


                }
            } catch (t: Throwable) {

            }
        }
    }
}
