package com.example.kursovik.UI.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentNewsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsFragment: Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.loadNews()
    }

    fun setupView() {
        viewModel.news.observe(viewLifecycleOwner) {
            adapter.addList(it)
        }
        configureRecycler()
    }

    fun configureRecycler() {
        adapter = NewsAdapter(context = requireContext(),
                              listener = object : OnPostClickListener {
            override fun onItemClick(item: Post) {
                view?.let {
                    val bundle = Bundle()
                    val gson = Gson()
                    val jsonString = gson.toJson(item)
                    bundle.putString("post", jsonString)
                    Navigation.findNavController(it).navigate(R.id.action_news_to_details, bundle);
                }

            }
        })

        binding.recyclerNews.setLayoutManager( LinearLayoutManager(activity))
        binding.recyclerNews.adapter = adapter
        binding.recyclerNews.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}