package com.lx.history.activity

import android.view.View
import com.lx.history.R
import com.lx.history.base.BaseActivity
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *  记录事件页面
 *  Create by LucianBen on 2019/5/30.
 * */
class RecordEventActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_record_event

    override fun initView() {
        tvTitle.text="记录事件"
    }

    override fun initData() {

    }

    fun onPageClick(view:View){
        when (view.id) {
            R.id.ivLeftImage->removeActivity()
            R.id.tvButtonConfirm->removeActivity()
        }
    }

}
