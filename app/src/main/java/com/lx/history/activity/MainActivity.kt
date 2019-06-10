package com.lx.history.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lx.amap.DiscoverFragment
import com.lx.common.BaseActivity
import com.lx.common.StatusBarCompat
import com.lx.history.R
import com.lx.history.fragment.HomeFragment
import com.lx.history.fragment.MineFragment
import com.lx.history.fragment.SpeciesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    private val mFragment = ArrayList<Fragment>(4)

    private lateinit var adapter: Vp2Adapter

    override fun initView() {
        StatusBarCompat.compat(this, 0xFFFFF)
        //判断定位权限是否开启
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200
            )

        } else {
            initData()  //开始定位
        }

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

        var previousPosition = -1
        mainBottonView.setOnNavigationItemSelectedListener { menuItem ->
            var position = 0
            when (menuItem.itemId) {
                R.id.item_home -> position = 0
                R.id.item_species -> position = 1
                R.id.item_empty -> false
                R.id.item_discover -> position = 2
                R.id.item_mine -> position = 3
            }
            if (previousPosition != position) {
                mainViewpager.setCurrentItem(position, false)
                previousPosition = position
            }
            true
        }

        mainFab.setOnClickListener {
            jumpPage(MainMiddleActivity::class.java)
        }
    }


    override fun initData() {

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

    fun onSpeciesPaintingFragmentClick(view: View) {
        when (view.id) {
            R.id.tvSpeciesAllPainting -> jumpPage(PaintingAllActivity::class.java)
            R.id.tvSpeciesLongPainting -> jumpPage(PaintingLongActivity::class.java)
            R.id.tvSpeciesStagingPainting -> jumpPage(PaintingStagingActivity::class.java)
        }
    }


}
