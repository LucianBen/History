package com.lx.history.fragment

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lx.history.adapter.SpeciesPaintingAdapter
import com.lx.history.base.BaseFragment
import kotlinx.android.synthetic.main.layout_species_painting.view.*


class SpeciesPaintingFragment : BaseFragment() {

    companion object {
        fun create(): SpeciesPaintingFragment = SpeciesPaintingFragment()
    }

    override val layoutId: Int = com.lx.history.R.layout.layout_species_painting
    private var data: MutableList<String> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView(view: View) {
        view.rvSpeciesPainting.layoutManager = object : LinearLayoutManager(activity) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        view.rvSpeciesPainting.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        view.rvSpeciesPainting.itemAnimator = DefaultItemAnimator()

        for (i in 0..100) {
            data.add(i, "数字自增长 $i")
        }

        view.rvSpeciesPainting.adapter = SpeciesPaintingAdapter(context!!, data)
        view.rvSpeciesPainting.isFocusable = false
    }

    override fun initData(view: View) {
    }

}