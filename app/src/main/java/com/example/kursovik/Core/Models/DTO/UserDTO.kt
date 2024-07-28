package com.example.kursovik.Core.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("response")
    val users: List<UserDTO>?
)

@Serializable
data class UserDTO (
    @SerialName("id"         ) var id        : Int?    = null,
    @SerialName("bdate" ) var bdate : String? = null,
    @SerialName("first_name" ) var firstName : String? = null,
    @SerialName("last_name"  ) var lastName  : String? = null,
    @SerialName("photo_200"  ) var photo: String? = null,
    @SerialName("city"       ) var city: CityDTO? = null
)

@Serializable
data class CityDTO(
    @SerialName("id"    ) var id        : Int?    = null,
    @SerialName("title" ) var title : String? = null
)