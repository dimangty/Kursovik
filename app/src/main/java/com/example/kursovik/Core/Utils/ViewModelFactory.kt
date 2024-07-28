package com.example.kursovik.Core.Utils

import com.example.kursovik.Core.Models.DTO.NewsDTO
import com.example.kursovik.Core.Models.Poso.Post

class ViewModelFactory {
    fun getPosts(newsfeed: NewsDTO): List<Post> {
        val posts = newsfeed.items.filter { post ->
            post.type == "post"
        }

        val postViewModels = mutableListOf<Post>()

        for (post in posts) {
            if (post.sourceId > 0) {
                val profile = newsfeed.profiles.firstOrNull { profileInfo ->
                    profileInfo.id == post.sourceId
                }
                profile?.let {
                    postViewModels.add(Post.initFrom(post, it))
                }
            } else {
                val group = newsfeed.groups.firstOrNull { groupInfo ->
                    groupInfo.id == kotlin.math.abs(post.sourceId)
                }
                group?.let {
                    postViewModels.add(Post.initFrom(post, it))
                }
            }
        }

        return postViewModels
    }
}