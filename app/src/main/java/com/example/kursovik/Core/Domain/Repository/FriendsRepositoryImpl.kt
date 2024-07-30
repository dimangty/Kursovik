package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Domain.AuthorizationInfo
import com.example.kursovik.Core.Models.DTO.FriendsResponse
import com.example.kursovik.Core.Models.UserResponse
import com.example.kursovik.Network.NetworkService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(private val networkService: NetworkService): FriendsRepository {
    override suspend fun getFriends(): Result<FriendsResponse>  = networkCall {
        val token = AuthorizationInfo.token
        val fields = "bdate, city, country, photo_50, photo_200_orig, status"
        networkService.getFriends(token, "5.131", fields) }
    suspend inline fun <T> networkCall(crossinline block: suspend () -> Response<T>): Result<T> =
        runCatching {
            val res = block().body()
            res ?: throw IOException("Network error")
        }

}
