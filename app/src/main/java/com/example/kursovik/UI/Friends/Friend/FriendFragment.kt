package com.example.kursovik.UI.Friends.Friend

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kursovik.Core.Models.Poso.Friend
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentFriendsItemBinding
import com.google.gson.Gson

class FriendFragment: Fragment(R.layout.fragment_friends_item) {
    private lateinit var binding: FragmentFriendsItemBinding
    private lateinit var friend: Friend
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsItemBinding.inflate(inflater)
        val jsonStr= arguments?.getString("friend", "")
        friend = Gson().fromJson(jsonStr, Friend::class.java);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textFName.text = friend.firstName
        binding.textLName.text = friend.lastName
        binding.textStatus.text = friend.status
        Glide.with(this).load(friend.url).into(binding.imageView3)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (parentFragmentManager.backStackEntryCount > 0) {
                    findNavController().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}