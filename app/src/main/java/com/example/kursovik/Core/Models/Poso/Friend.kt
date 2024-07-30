package com.example.kursovik.Core.Models.Poso

import com.example.kursovik.Core.Models.DTO.FriendDTO

data class Friend(public var firstName: String = "",
                  public var lastName: String = "",
                  public var url: String = "",
                  public var status: String = "",
                  public var location: String = "") {

    companion object {
        fun initFrom(friend: FriendDTO) : Friend {
            var url = ""
            if (friend.photo100 != null) {
                url = friend.photo100!!
            } else if (friend.photo200 != null) {
                url = friend.photo200!!
            }

            if (friend.firstName != null && friend.lastName != null && friend.status != null) {
                return Friend(firstName = friend.firstName!!,
                    lastName = friend.lastName!!,
                    url = url,
                    status = friend.status!!)
            } else {
                return Friend()
            }

        }

    }
}