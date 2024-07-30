package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.UserResponse

interface ProfileRepository {
    suspend fun getUser(): Result<UserResponse>
}