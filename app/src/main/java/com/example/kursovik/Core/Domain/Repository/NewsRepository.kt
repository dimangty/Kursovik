package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.UserResponse

interface NewsRepository {
    suspend fun getNews(token: String): Result<UserResponse>
}