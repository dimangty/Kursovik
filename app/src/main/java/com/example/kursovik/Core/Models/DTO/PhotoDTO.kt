package com.example.kursovik.Core.Models.DTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val response: PhotosDTO
)

@Serializable
data class PhotosDTO(
    val items: List<PhotoDTO>,
)

@Serializable
data class PhotoDTO(
    var id: Int,
    var date: Int,
    var text: String,
    var sizes: List<ImageSize>
)

@Serializable
data class ImageSize(
    var width: Int,
    var height: Int,
    var type: String,
    var url: String,
)