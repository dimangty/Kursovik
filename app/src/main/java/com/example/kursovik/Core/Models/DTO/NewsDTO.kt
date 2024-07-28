package com.example.kursovik.Core.Models.DTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsfeedResponse(
    val response: NewsDTO
)

@Serializable
data class NewsDTO(
    val items: List<PostDTO>,
    val profiles: List<ProfileInfo>,
    val groups: List<GroupInfo>,
    @SerialName("next_from")
    val nextFrom: String
)

@Serializable
data class PostDTO(
    val type: String,
    @SerialName("source_id")
    val sourceId: Int,
    @SerialName("date")
    val unixDate: Long,
    @SerialName("text")
    val text: String = ""
)

@Serializable
data class ProfileInfo(
    val id: Int,
    val sex: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("photo_100")
    val photoUrl: String
)

@Serializable
data class GroupInfo(
    val id: Int,
    val name: String,
    @SerialName("photo_100")
    val photoUrl: String
)