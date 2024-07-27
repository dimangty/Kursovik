package com.example.kursovik.UI.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kursovik.R
import com.example.kursovik.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupView()
        viewModel.loadUser()

    }

    fun setupView() {
        viewModel.user.observe(viewLifecycleOwner) {
            if(it != null) {
                binding.firstName.text = it.firstName
                binding.lastName.text = it.lastName
            }
        }
    }
}