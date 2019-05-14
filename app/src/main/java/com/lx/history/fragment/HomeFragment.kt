package com.lx.history.fragment

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lx.history.R
import com.lx.history.adapter.HomeAdapter
import com.lx.history.base.BaseFragment
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
    private var pics: MutableList<String> = arrayListOf(
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557751474304&di=9bbb0c04a25c1464706a7524121bca0d&imgtype=0&src=http%3A%2F%2Fimg389.ph.126.net%2F3s1NhXc-KmAdGOgUfEc_Og%3D%3D%2F2402670401203373701.jpg",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557751519311&di=da7073cb0c11de363cb94983c48d008f&imgtype=0&src=http%3A%2F%2Fimage109.360doc.com%2FDownloadImg%2F2018%2F09%2F2815%2F145518489_4_20180928031611374.jpg",
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1870299652,83991116&fm=26&gp=0.jpg"
    )

    override fun initData(view: View) {
        for (i: Int in 0..23) {
            data.add(i, "${i + 105}")
        }
        view.mainHomeRecyclerView.adapter = HomeAdapter(activity!!, data, pics)
    }

}