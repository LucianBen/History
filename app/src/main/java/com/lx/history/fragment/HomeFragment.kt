package com.lx.history.fragment

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lx.history.R
import com.lx.history.adapter.HomeAdapter
import com.lx.history.base.BaseFragment
import kotlinx.android.synthetic.main.layout_mainview_timeline.*
import kotlinx.android.synthetic.main.layout_mainview_timeline.view.*

class HomeFragment : BaseFragment() {

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }

    override val layoutId: Int = R.layout.layout_mainview_timeline


    override fun initView(view: View) {
        view.mainHomeRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.mainHomeRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private var data: MutableList<String> = ArrayList()
    override fun initData(view: View) {
        for (i: Int in 0..23) {
            data.add(i, "" + i)
        }
        view.mainHomeRecyclerView.adapter = HomeAdapter(activity!!, data)
    }

}