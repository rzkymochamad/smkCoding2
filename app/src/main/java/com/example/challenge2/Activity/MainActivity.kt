package com.example.challenge2.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.challenge2.Adapter.ViewPagerAdapter
import com.example.challenge2.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val menuTeks = arrayOf("Teman", "Github", "Local", "Global")
    val menuIcon = arrayOf(
        R.drawable.ic_friends3,
        R.drawable.ic_github1,
        R.drawable.ic_information_point,
        R.drawable.ic_global
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
            }).attach()
    }
}
