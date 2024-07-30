package com.example.kursovik.UI.NewsItem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.DateUtils
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentNewsItemBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsItemFragment(): Fragment(R.layout.fragment_news_item) {
    private lateinit var binding: FragmentNewsItemBinding
    private lateinit var post: Post
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val jsonStr= arguments?.getString("post", "")

        post = Gson().fromJson(jsonStr, Post::class.java);
        binding = FragmentNewsItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textTitle.text = post.title
        binding.textDate.text = DateUtils.getDateString(post.date)
        binding.textPost.text = post.text

        Glide.with(this).load(post.url).into(binding.imageView3)
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