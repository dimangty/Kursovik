package com.example.kursovik.Network

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET(Endpoints.GET_AUDIO)
    suspend fun getAudio(): Response<String>

    @GET(Endpoints.GET_DIALOGS)
    suspend fun getDialogs(): Response<String>

    @GET(Endpoints.GET_FRIENDS)
    suspend fun getFriends(): Response<String>

    @GET(Endpoints.GET_MESSAGES)
    suspend fun getMessages(): Response<String>

    @GET(Endpoints.GET_USERS)
    suspend fun getUsers(): Response<String>

    @GET(Endpoints.GET_VIDEO)
    suspend fun getVideo(): Response<String>
}