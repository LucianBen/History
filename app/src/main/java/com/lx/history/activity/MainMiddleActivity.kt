package com.lx.history.activity

import com.lx.history.R
import com.lx.history.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main_middle.*
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *  Created by LucianBen on 2019/05/16.
 *  Describe: 点击主页面中间按钮后跳转的页面
 *            我的大事件页面
 */

class MainMiddleActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main_middle

    override fun initView() {
        tvTitle.text = "我的大事记"
        fabRecordEvent.setOnClickListener { jumpPage(RecordEventActivity::class.java) }
    }

    override fun initData() {

    }
}