package com.lx.history.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lx.history.R
import com.lx.history.base.BaseActivity
import com.lx.history.fragment.DiscoverFragment
import com.lx.history.fragment.HomeFragment
import com.lx.history.fragment.MineFragment
import com.lx.history.fragment.SpeciesFragment
import com.lx.history.view.StatusBarCompat
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
            jumpPage(MainMiddleActivity::class.java)
        }
    }

    //声明AMapLocationClientOption对象
    private var mLocationOption: AMapLocationClientOption? = null
    //声明AMapLocationClient类对象
    private var mLocationClient: AMapLocationClient? = null

    companion object {
        var cityCode: String = ""
        var longitude: Double = 0.0
        var latitude: Double = 0.0
    }

    override fun initData() {
        //初始化定位
        mLocationClient = AMapLocationClient(this)
        //初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置定位回调监听
        mLocationClient!!.setLocationOption(mLocationOption)
        mLocationClient!!.startLocation()
        mLocationClient!!.setLocationListener {
            if (it != null) {
                if (it.errorCode == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    latitude = it.latitude//获取纬度
                    longitude = it.longitude//获取经度
                    it.accuracy//获取精度信息
                    it.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    it.country//国家信息
                    it.province//省信息
                    it.city//城市信息
                    it.district//城区信息
                    it.street//街道信息
                    it.streetNum//街道门牌号信息
                    cityCode = it.cityCode//城市编码
                    it.adCode//地区编码
                    it.aoiName//获取当前定位点的AOI信息
                    it.buildingId//获取当前室内定位的建筑物Id
                    it.floor//获取当前室内定位的楼层
                    it.gpsAccuracyStatus//获取GPS的当前状态

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    showToast("定位失败，请查看GPS是否打开")
                }
            }

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

    fun onSpeciesPaintingFragmentClick(view: View) {
        when (view.id) {
            R.id.tvSpeciesAllPainting -> jumpPage(PaintingAllActivity::class.java)
            R.id.tvSpeciesLongPainting -> jumpPage(PaintingLongActivity::class.java)
            R.id.tvSpeciesStagingPainting -> jumpPage(PaintingStagingActivity::class.java)
        }
    }


    override fun onStop() {
        super.onStop()
        mLocationClient!!.stopLocation()//停止定位后，本地定位服务并不会被销毁
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient!!.onDestroy()//销毁定位客户端，同时销毁本地定位服务。
    }
}
