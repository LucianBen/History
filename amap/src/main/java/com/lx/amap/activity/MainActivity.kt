package com.lx.amap.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.MapView
import com.amap.api.maps.model.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.lucianBen.baselibrary.BaseActivity
import com.lucianBen.baselibrary.Com
import com.lx.amap.R
import com.lx.amap.bean.MultiPointAndTitleItem
import java.util.*

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {
        mMapView = findViewById(R.id.mapView)
        mMapView.onCreate(null)
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
            initMap()//初始化地图
        }
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
        mLocationOption!!.isOnceLocationLatest = true
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //设置定位回调监听
        mLocationClient!!.setLocationOption(mLocationOption)
        mLocationClient!!.startLocation()
//        mLocationClient!!.setLocationListener {
//            if (it != null) {
//                if (it.errorCode == 0) {
//                    //可在其中解析amapLocation获取相应内容。
//                    Com.latitude = it.latitude//获取纬度
//                    Com.longitude = it.longitude//获取经度
//                    it.accuracy//获取精度信息
//                    Com.location =
//                        it.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    it.country//国家信息
//                    it.province//省信息
//                    Com.cityName = it.city//城市信息
//                    it.district//城区信息
//                    it.street//街道信息
//                    it.streetNum//街道门牌号信息
//                    Com.cityCode = it.cityCode//城市编码
//                    it.adCode//地区编码
//                    it.aoiName//获取当前定位点的AOI信息
//                    it.buildingId//获取当前室内定位的建筑物Id
//                    it.floor//获取当前室内定位的楼层
//                    it.gpsAccuracyStatus//获取GPS的当前状态
//
//                } else {
//                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    showToast("定位失败，请查看GPS是否打开")
//                }
//
//            }
//
//        }

    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mLocationClient!!.stopLocation()//停止定位后，本地定位服务并不会被销毁
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient!!.onDestroy()//销毁定位客户端，同时销毁本地定位服务。
        mMapView.onDestroy()
    }

    private lateinit var mMapView: MapView
    // 中心点坐标
    private var centerLatLng: LatLng = LatLng(Com.latitude, Com.longitude)
    private var followMove = true
    val multiPointList = ArrayList<MultiPointAndTitleItem>()

    private fun initMap() {
        //初始化地图控制器对象
        var aMap: AMap? = null
        if (aMap == null) {
            aMap = mMapView.map
            val myLocationStyle = MyLocationStyle()
            //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER)

            aMap.myLocationStyle = myLocationStyle//设置定位蓝点的Style
            aMap.isMyLocationEnabled = true// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            aMap.uiSettings.isRotateGesturesEnabled = false//禁止旋转手势
            aMap.uiSettings.setLogoBottomMargin(-50)//隐藏logo
            aMap.uiSettings.isZoomControlsEnabled = false//隐藏缩放按钮
            aMap.setOnMyLocationChangeListener {
                if (followMove) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng(Com.latitude, Com.longitude)))
                }
            }  //禁止地图回到定位点
            aMap.setOnMapTouchListener {
                if (followMove) {
                    followMove = false
                }
            }//用户拖动地图后，不再跟随移动，直到用户点击定位按钮
            val query = PoiSearch.Query("博物馆", "140100", Com.cityCode)
            query.pageSize = 15// 设置每页最多返回多少条poiitem
            query.pageNum = 1//设置查询页码
            val poiSearch = PoiSearch(this, query)
            poiSearch.bound = PoiSearch.SearchBound(LatLonPoint(Com.latitude, Com.longitude), 10000)//设置周边搜索的中心点以及半径
            poiSearch.setOnPoiSearchListener(object : PoiSearch.OnPoiSearchListener {
                override fun onPoiItemSearched(p0: PoiItem, p1: Int) {

                }

                override fun onPoiSearched(result: PoiResult, rCode: Int) {//响应码:1000为成功，其他为失败
                    //解析result获取POI信息，绘制点
                    if (rCode == 1000) {
                        result.pois.forEach { item ->
                            multiPointList.add(
                                MultiPointAndTitleItem(
                                    LatLng(item.latLonPoint.latitude, item.latLonPoint.longitude),
                                    item.title,
                                    item.snippet
                                )
                            )
                        }
                        //异步加载附近的博物馆
                        LoadTask(aMap).execute()

                    }

                }
            })
            poiSearch.searchPOIAsyn()

        }

    }

    private fun showResultOnMap(list: List<MultiPointAndTitleItem>, aMap: AMap) {
        val overlayOptions = MultiPointOverlayOptions()
        overlayOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.location_marker)
            )
        )//设置图标
        overlayOptions.anchor(0.5f, 0.5f)//设置锚点

        aMap.addMultiPointOverlay(overlayOptions).setItems(list)//将规范化的点集交给海量点管理对象设置，待加载完毕即可看到海量点信息
        aMap.setOnMultiPointClickListener { it ->
            val marker = aMap.addMarker(
                MarkerOptions().position(it.latLng).title(it.title).snippet(it.snippet)
            )
            marker.showInfoWindow()//弹出信息框

            val endLatlngPoint = LatLonPoint(it.latLng.latitude, it.latLng.longitude)
            aMap.setOnInfoWindowClickListener {
                val bundle = Bundle()
                bundle.putString("title", it.title)
                bundle.putString("snippet", it.snippet)
                bundle.putParcelable("latlngPoint", endLatlngPoint)
                jumpPage(MapPlanningActivity::class.java, bundle)
            }
            true
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState)
    }

    private inner class LoadTask(private val aMap: AMap) :
        AsyncTask<Void, Void, List<MultiPointAndTitleItem>>() {

        override fun onPreExecute() {
            super.onPreExecute()

            showToast("读取数据中...")
        }

        override fun doInBackground(vararg params: Void): List<MultiPointAndTitleItem> {
            return multiPointList
        }

        override fun onPostExecute(multiPointItems: List<MultiPointAndTitleItem>) {
            super.onPostExecute(multiPointItems)
            showResultOnMap(multiPointItems, aMap)
            //缩放移动地图
            val pointList = mutableListOf<LatLng>()
            for (point in multiPointItems.indices) {
                pointList.add(point, multiPointItems[point].latLng)
            }
            zoomToSpanWithCenter(pointList, aMap)

        }
    }

    /**
     * 缩放移动地图，保证所有自定义marker在可视范围中，且地图中心点不变。
     */
    fun zoomToSpanWithCenter(pointList: MutableList<LatLng>, aMap: AMap) {
        if (pointList.isNotEmpty()) {
            val bounds = getLatLngBounds(centerLatLng, pointList)
            aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
        }
    }

    //根据中心点和自定义内容获取缩放bounds
    private fun getLatLngBounds(centerpoint: LatLng?, pointList: List<LatLng>): LatLngBounds {
        val b = LatLngBounds.builder()
        if (centerpoint != null) {
            for (i in pointList.indices) {
                val p = pointList[i]
                val p1 = LatLng(centerpoint.latitude * 2 - p.latitude, centerpoint.longitude * 2 - p.longitude)
                b.include(p)
                b.include(p1)
            }
        }
        return b.build()
    }

}
