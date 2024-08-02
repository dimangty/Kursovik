package com.example.kursovik.Network

import android.content.Context
import retrofit2.http.GET


object Endpoints {
    const val API = "/method"
    fun getVkApiBaseUrl(): String {
        return "https://api.vk.com/"
    }

    object VkUrl {
        const val GET_AUDIO = API + "/audio.get?"
        const val GET_DIALOGS = API + "/messages.getDialogs?"
        const val GET_FRIENDS = API + "/friends.get?"
        const val GET_MESSAGES = API + "/messages.getHistory?"
        const val GET_USERS = API + "/users.get?"
        const val GET_VIDEO = API + "/video.get?"
        const val SEND_MESSAGES = API + "/messages.send?"
        const val GET_USER = API + "/users.get"
        const val GET_NEWS = API + "/newsfeed.get"
        const val GET_PHOTOS = API + "/photos.getAll"
    }
}
