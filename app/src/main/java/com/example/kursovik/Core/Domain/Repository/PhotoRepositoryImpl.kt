package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Domain.AuthorizationInfo
import com.example.kursovik.Core.Models.DTO.PhotoResponse
import com.example.kursovik.Core.Models.UserResponse
import com.example.kursovik.Network.NetworkService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val networkService: NetworkService): PhotoRepository {
    override suspend fun getPhotos(): Result<PhotoResponse> = networkCall {
        val token = AuthorizationInfo.token
        networkService.getPhotos(token, "5.131", 0, 10) }
    suspend inline fun <T> networkCall(crossinline block: suspend () -> Response<T>): Result<T> =
        runCatching {
            val res = block().body()
            res ?: throw IOException("Network error")
        }
}