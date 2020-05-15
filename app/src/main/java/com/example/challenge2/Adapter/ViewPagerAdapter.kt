package com.example.challenge2.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challenge2.Fragment.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> {return HomeFragment()
            }
            1 -> {return ProvinsiFragment()
            }
            2 -> {return GithubFragment()
            }
            3 -> {return TipsFragment()
            }
            else -> {
                return GithubFragment()
            }

        }
    }

    override fun getItemCount():Int{
        return JUMLAH_MENU
    }
}