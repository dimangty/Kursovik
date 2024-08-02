package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.DTO.PhotoResponse

interface PhotoRepository {
    suspend fun getPhotos(): Result<PhotoResponse>
}