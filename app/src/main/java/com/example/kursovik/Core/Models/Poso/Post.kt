package com.example.kursovik.Core.Models.Poso

import com.example.kursovik.Core.Models.DTO.GroupInfo
import com.example.kursovik.Core.Models.DTO.NewsDTO
import com.example.kursovik.Core.Models.DTO.PostDTO
import com.example.kursovik.Core.Models.DTO.ProfileInfo
import java.util.Date
import java.util.UUID

data class Post (var id: UUID = UUID.randomUUID(),
                 public var url: String = "",
                 public var title: String = "",
                 public var text: String = "",
                 public var date: Date = Date(),
                 public var dateString: String = "") {

    companion object {
        fun initFrom(post: PostDTO, profile: ProfileInfo): Post {
            val result = Post()

            result.text = post.text
            result.date = Date(post.unixDate)
            result.title = profile.firstName
            if (profile.lastName.isNotEmpty()) {
                result.title += " " + profile.lastName
            }
            result.url = profile.photoUrl
            return result
        }

        fun initFrom(post: PostDTO, group: GroupInfo): Post {
            val result = Post()
            result.text = post.text
            result.date = Date(post.unixDate)
            result.title = group.name
            result.url = group.photoUrl
            return result
        }

        fun initFrom(title: String, text: String, url: String, dateString: String): Post {
            val result = Post()
            result.text = text
            result.dateString = dateString
            result.title = title
            result.url = url
            return result
        }
    }

}
