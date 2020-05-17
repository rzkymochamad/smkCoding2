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
            1 -> {return TotalFragment()
            }
            2 -> {return NewsFragment()
            }
            3 -> {return TipsFragment()
            }
            else -> {
                return HomeFragment()
            }

        }
    }

    override fun getItemCount():Int{
        return JUMLAH_MENU
    }
}