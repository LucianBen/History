package com.lx.history.fragment

import android.view.View
import com.lx.history.R
import com.lx.history.base.BaseFragment
import kotlinx.android.synthetic.main.layout_mainview_timeline.*

class HomeFragment : BaseFragment() {

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }

    override val layoutId: Int = R.layout.layout_mainview_timeline


    override fun initView(view: View) {
//        mainHomeRecyclerView
    }
}