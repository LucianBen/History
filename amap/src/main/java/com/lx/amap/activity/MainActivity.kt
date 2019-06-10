package com.lx.amap.activity

import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.lx.amap.R
import com.lx.common.BaseActivity
import com.lx.common.Com

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {

    }

    //声明AMapLocationClientOption对象
    private var mLocationOption: AMapLocationClientOption? = null
    //声明AMapLocationClient类对象
    private var mLocationClient: AMapLocationClient? = null

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
                    Com.latitude = it.latitude//获取纬度
                    Com.longitude = it.longitude//获取经度
                    it.accuracy//获取精度信息
                    Com.location = it.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    it.country//国家信息
                    it.province//省信息
                    Com.cityName = it.city//城市信息
                    it.district//城区信息
                    it.street//街道信息
                    it.streetNum//街道门牌号信息
                    Com.cityCode = it.cityCode//城市编码
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

    override fun onStop() {
        super.onStop()
        mLocationClient!!.stopLocation()//停止定位后，本地定位服务并不会被销毁
    }

     override fun onDestroy() {
        super.onDestroy()
        mLocationClient!!.onDestroy()//销毁定位客户端，同时销毁本地定位服务。
    }

}
