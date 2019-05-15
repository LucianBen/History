package com.lx.history.activity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.lx.history.R
import com.lx.history.base.BaseActivity
import com.lx.history.control.TabLayoutMediator
import com.lx.history.fragment.DiscoverFragment
import com.lx.history.fragment.HomeFragment
import com.lx.history.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_.*

class MainBaseActivity : BaseActivity() {

    override val layoutId = R.layout.activity_
    private lateinit var tabLayout: TabLayout
    val tabName = arrayListOf("时间轴", "发现", "我的")

    override fun initView() {
        mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItem(position: Int): Fragment =
                when (position) {
                    0 -> HomeFragment.create()
                    1 -> DiscoverFragment.create()
                    else -> MineFragment.create()
                }

            override fun getItemCount(): Int = tabName.size

        }

        tabLayout = findViewById(R.id.mainTabs)
        TabLayoutMediator(tabLayout, mainViewpager) { tab, postion ->
            tab.text = tabName[postion]
        }.attach()
    }

}