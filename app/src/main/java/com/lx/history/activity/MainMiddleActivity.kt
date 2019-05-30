package com.lx.history.activity

import com.lx.history.R
import com.lx.history.base.BaseActivity
import com.lx.history.view.StatusBarCompat
import kotlinx.android.synthetic.main.activity_main_middle.*

/**
 *  Created by LucianBen on 2019/05/16.
 *  Describe: 点击主页面中间按钮后跳转的页面
 *            我的大事件页面
 */

class MainMiddleActivity : BaseActivity() {
    override fun initData() {

    }

    override val layoutId: Int = R.layout.activity_main_middle

    override fun initView() {
        fabRecordEvent.setOnClickListener { jumpPage(RecordEventActivity::class.java) }
    }

}