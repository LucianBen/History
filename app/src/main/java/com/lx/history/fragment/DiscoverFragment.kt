package com.lx.history.fragment

import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.MapView
import com.amap.api.maps.model.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.amap.api.services.poisearch.PoiSearch.*
import com.lx.history.R
import com.lx.history.activity.MainActivity.Companion.cityCode
import com.lx.history.activity.MainActivity.Companion.latitude
import com.lx.history.activity.MainActivity.Companion.longitude
import com.lx.history.base.BaseFragment
import java.util.*


class DiscoverFragment : BaseFragment() {

    private lateinit var mMapView: MapView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mRoot = inflater.inflate(R.layout.layout_mainview_discovery, container, false)
        mMapView = mRoot.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)
        initView(mRoot)
        return mRoot
    }

    override fun initView(view: View) {
        initMap()//获取地图信息
    }


    private var followMove = true
    val multiPointList = ArrayList<MultiPointItem>()

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
            aMap.setOnMyLocationChangeListener {
                //禁止地图回到定位点
                if (followMove) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng(latitude, longitude)))
                }
            }
            aMap.setOnMapTouchListener {
                if (followMove) {
                    //用户拖动地图后，不再跟随移动，直到用户点击定位按钮
                    followMove = false
                }
            }


            val query = Query("博物馆", "140100", cityCode)
            query.pageSize = 10// 设置每页最多返回多少条poiitem
            query.pageNum = 1//设置查询页码
            val poiSearch = PoiSearch(activity, query)
            poiSearch.bound = SearchBound(LatLonPoint(latitude, longitude), 10000)//设置周边搜索的中心点以及半径
            poiSearch.setOnPoiSearchListener(object : OnPoiSearchListener {
                override fun onPoiItemSearched(p0: PoiItem, p1: Int) {

                }

                override fun onPoiSearched(result: PoiResult, rCode: Int) {//响应码:1000为成功，其他为失败
                    //解析result获取POI信息，绘制点

                    if (rCode == 1000) {

                        for (item: PoiItem in result.pois) {
                            multiPointList.add(
                                MultiPointItem(
                                    LatLng(
                                        item.latLonPoint.latitude,
                                        item.latLonPoint.longitude
                                    )
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

    private fun showResultOnMap(list: List<MultiPointItem>, aMap: AMap) {
        val overlayOptions = MultiPointOverlayOptions()
        overlayOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.location_marker)
            )
        )
        overlayOptions.anchor(0.5f, 0.5f)

        val multiPointOverlay = aMap.addMultiPointOverlay(overlayOptions)
        multiPointOverlay.setItems(list)
        aMap.setOnMultiPointClickListener {

            return@setOnMultiPointClickListener false
        }
    }

    override fun initData(view: View) {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMapView.onDestroy()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState)
    }

    private inner class LoadTask(private val aMap: AMap) :
        AsyncTask<Void, Void, List<MultiPointItem>>() {

        override fun onPreExecute() {
            super.onPreExecute()

            showToast("读取数据中...")
        }

        override fun doInBackground(vararg params: Void): List<MultiPointItem> {
            return multiPointList
        }

        override fun onPostExecute(multiPointItems: List<MultiPointItem>) {
            super.onPostExecute(multiPointItems)
            showResultOnMap(multiPointItems, aMap)
        }
    }

}