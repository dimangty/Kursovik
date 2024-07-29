package com.example.kursovik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import com.example.kursovik.UI.TabPageAdapter
import com.example.kursovik.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject lateinit var progressService: ProgresService
    @Inject lateinit var errorService: ErrorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabBar()

        val intent = intent
        if (MainActivity.userId.isEmpty()) {
            val user = intent.getStringExtra("id")
            if (user != null) {
                MainActivity.userId = user
            }
        }

        if (MainActivity.token.isEmpty()) {
            val token = intent.getStringExtra("token")
            if (token != null) {
                MainActivity.token = token
            }
        }

        setup()
    }

    private fun setUpTabBar()
    {
        val adapter = TabPageAdapter(this, 5)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    fun setup() {
        progressService.show.observe(this, Observer {
            //your code here
            if (it) {
                progressService.showLoadingDialog(this)
            } else {
                progressService.hideLoading()
            }
        })

        errorService.show.observe(this, Observer {
            //your code here
            if (it.isNotEmpty()) {
                errorService.show(it, this)
            }
        })


    }

    override fun onResume() {
        super.onResume()
    }


    companion object {
        var token: String = ""
        var userId: String = ""
    }

}