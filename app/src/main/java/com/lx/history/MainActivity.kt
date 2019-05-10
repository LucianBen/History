package com.lx.history

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.lx.history.base.BaseFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseFragmentActivity() {

    override val layoutId = R.layout.activity_main
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewpager.adapter = ViewpagerAdapter()
        tabLayout = findViewById(R.id.mainTabs)
        TabLayoutMediator(tabLayout, mainViewpager) { tab, postion ->
            tab.text = TabName.TABNAME[postion]
        }.attach()


    }
}
