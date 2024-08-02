package com.example.kursovik.Core.Utils

import com.example.kursovik.Core.Models.DTO.FriendsResponse
import com.example.kursovik.Core.Models.DTO.NewsDTO
import com.example.kursovik.Core.Models.DTO.PhotoResponse
import com.example.kursovik.Core.Models.DTO.PhotosDTO
import com.example.kursovik.Core.Models.Poso.Friend
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


    fun getFriends(response: FriendsResponse): List<Friend> {
        val friends = mutableListOf<Friend>()
        if (response.response.friends != null) {
            for (friend in response.response.friends!!) {
                friends.add(Friend.initFrom(friend))
            }
        }

        return friends
    }

    fun getPhotos(response: PhotosDTO): List<String> {
        val photos = mutableListOf<String>()
        for (photo in response.items) {
            val url = photo.sizes.last().url
            photos.add(url)
        }

        return photos
    }
}