package com.lx.history.activity

import com.lx.history.R
import com.lx.history.base.BaseActivity
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *  全部画作界面
 *  Created by LucianBen on 2019/05/31.
 *  瀑布流的形式展示
 * */
class PaintingAllActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_painting_all

    override fun initView() {
        tvTitle.text = "全部画作"
    }

    override fun initData() {

    }

}
