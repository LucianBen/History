package com.lx.myevent.activity

import android.view.View
import com.lucianBen.baselibrary.BaseActivity
import com.lx.myevent.R

/**
 *  Created by LucianBen on 2019/6/12
 *  Describe:  记录事件页面
 */
class RecordEventActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_record_event

    override fun initView() {

    }

    override fun initData() {

    }

    fun onPageClick(view: View) {
        when (view.id) {
            R.id.tvButtonConfirm -> removeActivity()
        }
    }

}
