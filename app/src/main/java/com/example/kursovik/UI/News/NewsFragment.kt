package com.example.kursovik.UI.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentNewsBinding
import com.example.kursovik.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        setupView()
        viewModel.loadNews()
    }

    fun setupView() {

    }
}