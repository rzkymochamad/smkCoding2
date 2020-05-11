package com.example.challenge2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> {return MyFriendFragment()}
            1 -> {return GithubFragment()}
            2 -> {return ProfileFragment()}
            3 -> {return HomeFragment()}
            else -> {
                return GithubFragment()
            }

        }
    }

    override fun getItemCount():Int{
        return JUMLAH_MENU
    }
}