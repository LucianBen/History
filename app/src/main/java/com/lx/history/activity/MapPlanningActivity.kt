package com.lx.history.activity

import android.content.Intent
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.amap.api.services.route.RouteSearch.DriveRouteQuery
import com.lx.history.R
import com.lx.history.base.BaseActivity
import com.lx.history.base.Com
import com.lx.history.util.AMapUtil
import kotlinx.android.synthetic.main.activity_map_planning.*
import overlay.DrivingRouteOverlay


class MapPlanningActivity : BaseActivity(), RouteSearch.OnRouteSearchListener {


    override val layoutId: Int = R.layout.activity_map_planning

    private lateinit var mapView: MapView
    private val mStartPoint = LatLonPoint(39.942295, 116.335891)//起点，39.942295,116.335891
    private val mEndPoint = LatLonPoint(39.995576, 116.481288)//终点，39.995576,116.481288
    private lateinit var aMap: AMap
    override fun initView() {
        mapView = findViewById(R.id.mapViewPlanning)
        mapView.onCreate(null)

        val fromAndTo = RouteSearch.FromAndTo(
            mStartPoint, mEndPoint
        )

        setfromandtoMarker()
        val routeSearch = RouteSearch(this)
        // fromAndTo包含路径规划的起点和终点，drivingMode表示驾车模式
        // 第三个参数表示途经点（最多支持16个），第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
        val query = DriveRouteQuery(
            fromAndTo,
            Com.DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION, null, null, ""
        )
        routeSearch.calculateDriveRouteAsyn(query)
        routeSearch.setRouteSearchListener(this)

    }

    override fun initData() {

    }

    private fun setfromandtoMarker() {
        aMap = mapView.map

        aMap.addMarker(
            MarkerOptions()
                .position(LatLng(mStartPoint.latitude, mStartPoint.longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker))
        )
        aMap.addMarker(
            MarkerOptions()
                .position(LatLng(mEndPoint.latitude, mEndPoint.longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker))
        )

    }

    private lateinit var mDriveRouteResult: DriveRouteResult
    override fun onDriveRouteSearched(result: DriveRouteResult, errorCode: Int) {
        aMap.clear()// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result.paths != null) {
                if (result.paths.size > 0) {
                    mDriveRouteResult = result
                    val drivePath = mDriveRouteResult.paths[0] ?: return

                    val drivingRouteOverlay = DrivingRouteOverlay(
                        this, aMap, drivePath,
                        mDriveRouteResult.startPos,
                        mDriveRouteResult.targetPos, null
                    )

                    drivingRouteOverlay.setNodeIconVisibility(false)//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true)//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap()
                    drivingRouteOverlay.addToMap()
                    drivingRouteOverlay.zoomToSpan()
                    bottomLayout.visibility = View.VISIBLE
                    val dis = drivePath.distance.toInt()
                    val dur = drivePath.duration.toInt()
                    val des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")"
                    firstline.text = des
                    secondline.visibility = View.VISIBLE
                    val taxiCost = mDriveRouteResult.taxiCost.toInt()
                    secondline.text = "打车约 $taxiCost 元"
                    bottomLayout.setOnClickListener {
                        val intent = Intent(
                            this,
                            DriveRouteDetailActivity::class.java
                        )
                        intent.putExtra("drive_path", drivePath)
                        intent.putExtra(
                            "drive_result",
                            mDriveRouteResult
                        )
                        startActivity(intent)
                    }

                } else if (result.paths == null) {
                    showToast(getString(R.string.no_result))
                }

            } else {
                showToast(getString(R.string.no_result))
            }
        } else {
            showToast(getString(errorCode))
        }
    }

    override fun onBusRouteSearched(p0: BusRouteResult?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRideRouteSearched(p0: RideRouteResult?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onWalkRouteSearched(p0: WalkRouteResult?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
