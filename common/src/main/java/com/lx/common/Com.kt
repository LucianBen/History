package com.lx.common

/**
 *  Created by LucianBen on 2019/6/5
 *  Describe:
 */

class Com {

    companion object {

        const val DRIVING_SINGLE_NO_HIGHWAY = 6//速度优先，不走高速，但是不排除走其余收费路段
        const val DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION = 10//返回结果会躲避拥堵，路程较短，尽量缩短时间
        var cityCode: String = ""
        var cityName: String = ""
        var longitude: Double = 0.0
        var latitude: Double = 0.0
        var location: String = ""

        var routeType: Int = 0//用户选择规划路线的方式  0:步行   1:骑车   2:驾车   3:公交

        const val isDebug = true

    }
}