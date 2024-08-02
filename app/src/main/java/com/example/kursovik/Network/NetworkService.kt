package com.example.kursovik.Network

import com.example.kursovik.Core.Models.DTO.FriendsResponse
import com.example.kursovik.Core.Models.DTO.NewsfeedResponse
import com.example.kursovik.Core.Models.DTO.PhotoResponse
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
    suspend fun getFriends(@Query("access_token") token: String, @Query("v") v:String, @Query("fields") fields: String): Response<FriendsResponse>

    @GET(Endpoints.VkUrl.GET_MESSAGES)
    suspend fun getMessages(): Response<String>

    @GET(Endpoints.VkUrl.GET_USERS)
    fun getUsers(): Response<UserResponse>

    @GET(Endpoints.VkUrl.GET_VIDEO)
    suspend fun getVideo(): Response<String>

    @GET(Endpoints.VkUrl.GET_NEWS)
    suspend fun getNews(@Query("access_token") token: String, @Query("v") v:String): Response<NewsfeedResponse>

    @GET(Endpoints.VkUrl.GET_USER)
    suspend fun getUser(@Query("access_token") token: String, @Query("v") v:String, @Query("fields") fields: String): Response<UserResponse>

    @GET(Endpoints.VkUrl.GET_PHOTOS)
    suspend fun getPhotos(@Query("access_token") token: String, @Query("v") v:String, @Query("offset") offset: Int, @Query("count") count: Int,): Response<PhotoResponse>

}