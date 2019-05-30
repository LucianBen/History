package com.lx.history.fragment

import android.view.View
import com.lx.history.R
import com.lx.history.activity.VideoPlayerActivity
import com.lx.history.base.BaseFragment
import kotlinx.android.synthetic.main.layout_mainview_discovery.view.*

class DiscoverFragment : BaseFragment() {

    override val layoutId: Int = R.layout.layout_mainview_discovery

    override fun initView(view: View) {
        view.tvClick.setOnClickListener { jumpPage(VideoPlayerActivity::class.java) }
    }

    override fun initData(view: View) {
    }


}