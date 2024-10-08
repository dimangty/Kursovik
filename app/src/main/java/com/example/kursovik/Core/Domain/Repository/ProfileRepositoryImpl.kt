package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Domain.AuthorizationInfo
import com.example.kursovik.Core.Models.UserResponse
import com.example.kursovik.Network.NetworkService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val networkService: NetworkService): ProfileRepository {
    override suspend fun getUser(): Result<UserResponse> = networkCall {
        val token = AuthorizationInfo.token
        val fields = "bdate, city, country, photo_200, status"
        networkService.getUser(token, "5.131", fields) }
    suspend inline fun <T> networkCall(crossinline block: suspend () -> Response<T>): Result<T> =
        runCatching {
            val res = block().body()
            res ?: throw IOException("Network error")
        }

}