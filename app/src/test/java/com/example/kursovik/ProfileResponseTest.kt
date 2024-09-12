package com.example.kursovik

import com.example.kursovik.Core.Models.UserResponse
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test


class ProfileResponseTest {
    private val jsonResponse = "{\"response\":[{\"id\":9682231,\"bdate\":\"19.7.1988\",\"city\":{\"id\":99,\"title\":\"Новосибирск\"},\"photo_200\":\"https:\\/\\/sun4-19.userapi.com\\/s\\/v1\\/ig1\\/9CHvfYj-slWdnKR85-jlWamOM0V60DsPO1w-IwS8nlbj4v6auUz7YSFeQVZmWjV3UUlL6jMQ.jpg?quality=96&crop=984,143,607,607&as=32x32,48x48,72x72,108x108,160x160,240x240,360x360,480x480,540x540&ava=1&cs=200x200\",\"status\":\"\",\"first_name\":\"Дмитрий\",\"last_name\":\"Быков\",\"can_access_closed\":true,\"is_closed\":false}]}"

    @Test
    fun `test parsing of Profile JSON response`() {
        val json = Json{ignoreUnknownKeys = true}
        val profile = json.decodeFromString<UserResponse>(jsonResponse)
        Assert.assertEquals("19.7.1988", profile.users?.first()?.bdate)
    }

}