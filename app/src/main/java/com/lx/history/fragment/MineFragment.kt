package com.lx.history.fragment

import android.view.View
import com.lx.history.R
import com.lx.history.base.BaseFragment

class MineFragment : BaseFragment() {

    override val layoutId: Int = R.layout.layout_mainview_mine

    private lateinit var title: String

    override fun initView(view: View) {
        title = arguments!!.getString("title")
    }

    override fun initData(view: View) {
    }


}