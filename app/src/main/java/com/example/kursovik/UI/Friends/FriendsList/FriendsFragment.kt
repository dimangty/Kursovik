package com.example.kursovik.UI.Friends.FriendsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovik.Core.Models.Poso.Friend
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.R
import com.example.kursovik.UI.News.NewsAdapter
import com.example.kursovik.UI.News.OnPostClickListener
import com.example.kursovik.databinding.FragmentFriendsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment: Fragment(R.layout.fragment_friends) {
    private lateinit var adapter: FriendsAdapter
    private lateinit var binding: FragmentFriendsBinding
    private val viewModel: FriendsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadFriends()
        setupView()
    }


    fun setupView() {
        viewModel.friends.observe(viewLifecycleOwner) {
            adapter.addList(it)
        }
        configureRecycler()
    }

    fun configureRecycler() {
        adapter = FriendsAdapter(context = requireContext(),
            listener = object : OnFriendClickListener {
                override fun onItemClick(item: Friend) {
                    view?.let {
                        val bundle = Bundle()
                        val gson = Gson()
                        val jsonString = gson.toJson(item)
                        bundle.putString("friend", jsonString)
                        Navigation.findNavController(it).navigate(R.id.action_friends_to_details, bundle);
                    }

                }
            })

        binding.recyclerFriends.setLayoutManager( LinearLayoutManager(activity))
        binding.recyclerFriends.adapter = adapter
        binding.recyclerFriends.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}