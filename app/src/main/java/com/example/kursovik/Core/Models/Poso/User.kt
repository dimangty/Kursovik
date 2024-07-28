package com.example.kursovik.Core.Models.Poso

import com.example.kursovik.Core.Models.UserDTO
import kotlinx.serialization.SerialName

data class User(
    var firstName : String? = null,
    var lastName  : String? = null,
    var bDate  : String? = null,
    var city  : String? = null,
    var photo : String? = null
) {
    fun initFrom(user: UserDTO) : User {
        this.firstName = user.firstName
        this.lastName = user.lastName
        this.bDate = user.bdate
        this.city = user.city?.title
        this.photo = user.photo
        return this
    }
}