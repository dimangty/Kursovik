package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.DTO.NewsfeedResponse

interface NewsRepository {
    suspend fun getNews(token: String): Result<NewsfeedResponse>
}