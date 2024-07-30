package com.example.kursovik.UI

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kursovik.UI.Friends.FriendsRoot.FriendsRootFragment
import com.example.kursovik.UI.News.NewsFragment
import com.example.kursovik.UI.News.NewsRoot.NewsRootFragment
import com.example.kursovik.UI.Profile.ProfileFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when (position)
        {
            0 -> ProfileFragment()
            1 -> NewsRootFragment()
            2 -> FriendsRootFragment()
            else -> ProfileFragment()
        }
    }

}