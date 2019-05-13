package com.lx.history.fragment

import android.view.View
import com.lx.history.R
import com.lx.history.base.BaseFragment

class MineFragment : BaseFragment() {
    companion object {
        fun create(): MineFragment = MineFragment()
    }

    override val layoutId: Int
        get() = R.layout.layout_mainview_mine

    override fun initView(view: View) {

    }

    override fun initData(view: View) {
    }


}