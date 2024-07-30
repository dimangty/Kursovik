package com.example.kursovik.Core.Models.DTO

import com.example.kursovik.Core.Models.CityDTO
import com.example.kursovik.Core.Models.UserDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendsResponse(
    @SerialName("response")
    val response: Friends
)

@Serializable
data class Friends(
    @SerialName("items")
    val friends: List<FriendDTO>?
)

@Serializable
data class FriendDTO(
    @SerialName("id"         ) var id        : Int?    = null,
    @SerialName("bdate" ) var bdate : String? = null,
    @SerialName("first_name" ) var firstName : String? = null,
    @SerialName("last_name"  ) var lastName  : String? = null,
    @SerialName("photo_100") var photo100: String? = null,
    @SerialName("photo_200_orig") var photo200: String? = null,
    @SerialName("city"       ) var city: CityDTO? = null,
    @SerialName("country"    ) var country: CityDTO? = null,
    @SerialName("status"    ) var status: String? = null
)
