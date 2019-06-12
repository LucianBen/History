package com.lx.amap.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.lucianBen.baselibrary.BaseActivity
import com.lucianBen.baselibrary.StatusBarCompat
import com.lx.amap.R
import com.lx.overlay.util.AMapUtil
import kotlinx.android.synthetic.main.activity_map_planning.*

/**
 *  Created by LucianBen on 2019/6/10
 *  Describe: 步行、骑车、驾车  地图规划路线
 */

class MapPlanningActivity : BaseActivity(), RouteSearch.OnRouteSearchListener {
    override val layoutId: Int = R.layout.activity_map_planning

    private lateinit var mapView: MapView
    private lateinit var mStartPoint: LatLonPoint
    private lateinit var mEndPoint: LatLonPoint
    private lateinit var aMap: AMap
    private lateinit var fromAndTo: RouteSearch.FromAndTo

    private val itemArr = listOf("步行", "骑行", "驾车", "公交")

    override fun initView() {
        StatusBarCompat.compat(this, 0xFFFFFFFF.toInt())

        mapView = findViewById(R.id.mapViewPlanning)
        mapView.onCreate(null)
        aMap = mapView.map

    }

    override fun initData() {
        etStartPoint.text = Editable.Factory.getInstance().newEditable("我的位置")
        etEndPoint.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("title"))

        mStartPoint = LatLonPoint(com.lucianBen.baselibrary.Com.latitude, com.lucianBen.baselibrary.Com.longitude)//起点
        mEndPoint = intent.getParcelableExtra("latlngPoint")
        fromAndTo = RouteSearch.FromAndTo(mStartPoint, mEndPoint)

        val routeSearch = RouteSearch(this)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemArr)
        spinnerChooseRoute.setSelection(0, true)
        spinnerChooseRoute.adapter = adapter
        spinnerChooseRoute.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                com.lucianBen.baselibrary.Com.routeType = pos
                bottomLayout.visibility = View.VISIBLE
                busResult.visibility = View.GONE

                when (com.lucianBen.baselibrary.Com.routeType) {
                    0 -> routeSearch.calculateWalkRouteAsyn(RouteSearch.WalkRouteQuery(fromAndTo))
                    1 -> routeSearch.calculateRideRouteAsyn(RouteSearch.RideRouteQuery(fromAndTo))
                    2 -> routeSearch.calculateDriveRouteAsyn(
                        RouteSearch.DriveRouteQuery(
                            // fromAndTo包含路径规划的起点和终点，drivingMode表示驾车模式
                            // 第三个参数表示途经点（最多支持16个），第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
                            fromAndTo,
                            com.lucianBen.baselibrary.Com.DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION,
                            null,
                            null,
                            ""
                        )
                    )
                    3 -> {
                        bottomLayout.visibility = View.GONE
                        busResult.visibility = View.VISIBLE

                        routeSearch.calculateBusRouteAsyn(
                            RouteSearch.BusRouteQuery(
                                // 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，
                                // 第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
                                fromAndTo, RouteSearch.BUS_DEFAULT, com.lucianBen.baselibrary.Com.cityName, 0
                            )
                        )
                    }

                }

            }
        }
        routeSearch.setRouteSearchListener(this)

    }


    /**
     * 驾车路径规划
     * */
    override fun onDriveRouteSearched(driveRouteResult: DriveRouteResult, errorCode: Int) {
        aMap.clear()// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (driveRouteResult.paths != null) {
                if (driveRouteResult.paths.size > 0) {
                    val drivePath = driveRouteResult.paths[0] ?: return

                    val drivingRouteOverlay =
                        com.lx.overlay.DrivingRouteOverlay(
                            this, aMap, drivePath,
                            driveRouteResult.startPos, driveRouteResult.targetPos, null
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
                    val taxiCost = driveRouteResult.taxiCost.toInt()
                    secondline.text = "打车约 $taxiCost 元"
                    bottomLayout.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putParcelable("drive_path", drivePath)
                        bundle.putParcelable("drive_result", driveRouteResult)
                        jumpPage(DriveRouteDetailActivity::class.java, bundle)
                    }

                } else if (driveRouteResult.paths == null) {
                    showToast(getString(R.string.no_result))
                }

            } else {
                showToast(getString(R.string.no_result))
            }
        } else {
            showToast(getString(errorCode))
        }
    }

    /**
     * 公交路径规划
     * */
    override fun onBusRouteSearched(busRouteResult: BusRouteResult?, errorCode: Int) {
        aMap.clear()// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (busRouteResult?.paths != null) {
                if (busRouteResult.paths.size > 0) {
                    busResultList.adapter = com.lx.amap.adapter.BusResultListAdapter(this, busRouteResult)
                } else if (busRouteResult.paths == null) {
                    showToast(getString(R.string.no_result))
                }
            } else {
                showToast(getString(R.string.no_result))
            }
        } else {
            showToast(getString(errorCode) + "")
        }
    }

    /**
     * 骑行路径规划
     * */
    override fun onRideRouteSearched(rideRouteResult: RideRouteResult?, errorCode: Int) {
        aMap.clear()// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (rideRouteResult?.paths != null) {
                if (rideRouteResult.paths.size > 0) {
                    val ridePath = rideRouteResult.paths[0] ?: return
                    val rideRouteOverlay =
                        com.lx.overlay.RideRouteOverlay(
                            this,
                            aMap,
                            ridePath,
                            rideRouteResult.startPos,
                            rideRouteResult.targetPos
                        )
                    rideRouteOverlay.removeFromMap()
                    rideRouteOverlay.addToMap()
                    rideRouteOverlay.zoomToSpan()
                    bottomLayout.visibility = View.VISIBLE
                    val dis = ridePath.distance.toInt()
                    val dur = ridePath.duration.toInt()
                    val des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")"
                    firstline.text = des
                    secondline.visibility = View.GONE
                    bottomLayout.setOnClickListener {
                        val intent = Intent(this, DriveRouteDetailActivity::class.java)
                        intent.putExtra("ride_path", ridePath)
                        intent.putExtra("ride_result", rideRouteResult)
                        startActivity(intent)
                    }
                } else if (rideRouteResult.paths == null) {
                    showToast(getString(R.string.no_result))
                }
            } else {
                showToast(getString(R.string.no_result))
            }
        } else {
            showToast(getString(errorCode))
        }
    }

    /**
     * 步行路径规划
     * */
    override fun onWalkRouteSearched(walkRouteResult: WalkRouteResult?, errorCode: Int) {
        aMap.clear()// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (walkRouteResult?.paths != null) {
                if (walkRouteResult.paths.size > 0) {
                    val walkPath = walkRouteResult.paths[0] ?: return
                    val walkRouteOverlay =
                        com.lx.overlay.WalkRouteOverlay(
                            this,
                            aMap,
                            walkPath,
                            walkRouteResult.startPos,
                            walkRouteResult.targetPos
                        )
                    walkRouteOverlay.removeFromMap()
                    walkRouteOverlay.addToMap()
                    walkRouteOverlay.zoomToSpan()
                    bottomLayout.visibility = View.VISIBLE
                    val dis = walkPath.distance.toInt()
                    val dur = walkPath.duration.toInt()
                    val des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")"
                    firstline.text = des
                    secondline.visibility = View.GONE
                    bottomLayout.setOnClickListener {
                        val intent = Intent(this, DriveRouteDetailActivity::class.java)
                        intent.putExtra("walk_path", walkPath)
                        intent.putExtra("walk_result", walkRouteResult)
                        startActivity(intent)
                    }
                } else if (walkRouteResult.paths == null) {
                    showToast(getString(R.string.no_result))
                }
            } else {
                showToast(getString(R.string.no_result))
            }
        } else {
            showToast(getString(errorCode))
        }
    }


}
