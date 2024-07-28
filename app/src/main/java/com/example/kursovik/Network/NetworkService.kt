package com.example.kursovik.Network

import com.example.kursovik.Core.Models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET(Endpoints.VkUrl.GET_AUDIO)
    suspend fun getAudio(): Response<String>

    @GET(Endpoints.VkUrl.GET_DIALOGS)
    suspend fun getDialogs(): Response<String>

    @GET(Endpoints.VkUrl.GET_FRIENDS)
    suspend fun getFriends(): Response<String>

    @GET(Endpoints.VkUrl.GET_MESSAGES)
    suspend fun getMessages(): Response<String>

    @GET(Endpoints.VkUrl.GET_USERS)
    fun getUsers(): Response<UserResponse>

    @GET(Endpoints.VkUrl.GET_VIDEO)
    suspend fun getVideo(): Response<String>

    @GET(Endpoints.VkUrl.GET_NEWS)
    suspend fun getNews(@Query("access_token") token: String, @Query("v") v:String): Response<UserResponse>

    @GET(Endpoints.VkUrl.GET_USER)
    suspend fun getUser(@Query("access_token") token: String, @Query("v") v:String, @Query("fields") fields: String): Response<UserResponse>
}