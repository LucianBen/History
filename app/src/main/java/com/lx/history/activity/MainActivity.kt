package com.lx.history.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lx.history.R
import com.lx.history.base.BaseActivity
import com.lx.history.fragment.DiscoverFragment
import com.lx.history.fragment.HomeFragment
import com.lx.history.fragment.MineFragment
import com.lx.history.fragment.SpeciesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    private val mFragment = ArrayList<Fragment>(4)

    private lateinit var adapter: Vp2Adapter

    override fun initView() {
        val homeFragment = HomeFragment()
        val speciesFragment = SpeciesFragment()
        val discoverFragment = DiscoverFragment()
        val mineFragment = MineFragment()
        val bundle = Bundle()
        bundle.putString("title", "noLogin")
        mineFragment.arguments = bundle

        mFragment.add(homeFragment)
        mFragment.add(speciesFragment)
        mFragment.add(discoverFragment)
        mFragment.add(mineFragment)

        mainBottonView.enableItemShiftingMode(false)
        mainBottonView.enableShiftingMode(false)
        mainBottonView.enableAnimation(false)

        adapter = Vp2Adapter(supportFragmentManager, mFragment)
        mainViewpager.adapter = adapter

        mainBottonView.onNavigationItemSelectedListener =
            object : BottomNavigationView.OnNavigationItemSelectedListener {
                private var previousPosition = -1
                override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                    var position = 0
                    when (menuItem.itemId) {
                        R.id.item_home -> position = 0
                        R.id.item_species -> position = 1
                        R.id.item_empty -> return false
                        R.id.item_discover -> position = 2
                        R.id.item_mine -> position = 3
                    }
                    if (previousPosition != position) {
                        mainViewpager.setCurrentItem(position, false)
                        previousPosition = position
                    }
                    return true
                }

            }
        mainFab.setOnClickListener {
           showToast("")
        }
    }

    class Vp2Adapter(fm: FragmentManager, private val data: List<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = data[position]

        override fun getCount(): Int = data.size

    }

    private var firstTime: Long = 0
    override fun onBackPressed() {
        val secondTime = System.currentTimeMillis()
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            firstTime = secondTime
        } else {
            removeAllActivity()
        }
    }

}
