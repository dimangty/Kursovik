package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Domain.AuthorizationInfo
import com.example.kursovik.Core.Models.DTO.NewsfeedResponse
import com.example.kursovik.Network.NetworkService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val networkService: NetworkService): NewsRepository {
    override suspend fun getNews(): Result<NewsfeedResponse> = networkCall {
        val token = AuthorizationInfo.token
        networkService.getNews(token, "5.131") }
    suspend inline fun <T> networkCall(crossinline block: suspend () -> Response<T>): Result<T> =
        runCatching {
            val res = block().body()
            res ?: throw IOException("Network error")
        }

}