package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.UserResponse
import com.example.kursovik.Network.NetworkService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ProfileRepositoryImpl@Inject constructor(private val networkService: NetworkService): ProfileRepository {
    override suspend fun getUser(token: String): Result<UserResponse> = networkCall { networkService.getUser(token, "5.131") }
    suspend inline fun <T> networkCall(crossinline block: suspend () -> Response<T>): Result<T> =
        runCatching {
            val res = block().body()
            res ?: throw IOException("Network error")
        }

}