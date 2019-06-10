package com.lx.history.activity

import com.lx.common.BaseActivity
import com.lx.history.R
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *  画作分期界面
 *  Created by LucianBen on 2019/05/31.
 *  ViewPager 和 TabLayout 可以滚动
 * */
class PaintingStagingActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.activity_painting_staging

    override fun initView() {
        tvTitle.text = "分期画作"
    }

    override fun initData() {

    }
}
