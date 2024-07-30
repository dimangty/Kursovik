package com.example.kursovik.Core.Domain.Repository

import com.example.kursovik.Core.Models.DTO.FriendsResponse


interface FriendsRepository {
    suspend fun getFriends(): Result<FriendsResponse>
}