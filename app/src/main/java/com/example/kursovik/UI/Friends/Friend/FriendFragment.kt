package com.example.kursovik.UI.Friends.Friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentFriendsItemBinding

class FriendFragment: Fragment(R.layout.fragment_friends_item) {
    private lateinit var binding: FragmentFriendsItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsItemBinding.inflate(inflater)
        return binding.root
    }
}