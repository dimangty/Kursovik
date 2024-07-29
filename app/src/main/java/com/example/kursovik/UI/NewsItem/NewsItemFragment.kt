package com.example.kursovik.UI.NewsItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.Core.Utils.DateUtils
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentNewsItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsItemFragment(private val post: Post): Fragment(R.layout.fragment_news_item) {
    private lateinit var binding: FragmentNewsItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textTitle.text = post.title
        binding.textDate.text = DateUtils.getDateString(post.date)
        binding.textTitle.text = post.title

        Glide.with(this).load(post.url).into(binding.imageView3)
    }


}