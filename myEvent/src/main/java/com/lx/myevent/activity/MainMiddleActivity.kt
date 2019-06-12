package com.lx.myevent.activity

import com.lucianBen.baselibrary.BaseActivity
import com.lx.myevent.R
import kotlinx.android.synthetic.main.activity_main_middle.*

/**
 *  Created by LucianBen on 2019/6/12
 *  Describe:  点击主页面中间按钮后跳转的页面
 *            我的大事件页面
 */

class MainMiddleActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main_middle

    override fun initView() {
        fabRecordEvent.setOnClickListener { jumpPage(RecordEventActivity::class.java) }
    }

    override fun initData() {

    }
}