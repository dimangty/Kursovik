package com.example.kursovik.UI.Photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovik.Core.Models.Poso.Post
import com.example.kursovik.R
import com.example.kursovik.UI.News.NewsAdapter
import com.example.kursovik.UI.News.OnPostClickListener
import com.example.kursovik.databinding.FragmentPhotosBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment: Fragment(R.layout.fragment_photos) {
    private lateinit var adapter: PhotosAdapter
    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.loadPhotos()
    }

    fun setupView() {
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.addList(it)
        }
        configureRecycler()
    }

    private fun configureRecycler() {
        adapter = PhotosAdapter(context = requireContext())

        binding.recyclerPhotos.setLayoutManager( GridLayoutManager(activity, 2))
        binding.recyclerPhotos.adapter = adapter
    }
}