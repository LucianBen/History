package com.lx.history.fragment

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lx.history.R
import com.lx.historyworks.adapter.SpeciesPaintingAdapter
import kotlinx.android.synthetic.main.layout_species_painting.view.*


class SpeciesPaintingFragment : com.lucianBen.baselibrary.BaseFragment() {

    companion object {
        fun create(): SpeciesPaintingFragment = SpeciesPaintingFragment()
    }

    override val layoutId: Int = R.layout.layout_species_painting
    private var data: MutableList<String> = ArrayList()

    override fun initView(view: View) {
        view.rvSpeciesPainting.layoutManager = object : LinearLayoutManager(activity) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
//        view.rvSpeciesPainting.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        view.rvSpeciesPainting.itemAnimator = DefaultItemAnimator()

        for (i in 0..7) {
            data.add(
                i,
                "数字自增长 $i 时间的考验最残酷，却也最公平。降生到人间走一遭，" +
                        "是呕心沥血为了他人活的更好，还是斤斤计较为了自己这一身臭皮囊算计，" +
                        "为了让自己活的更久，还在受骗的买什么仙丹。说不清，道不明，就去问问时间吧！" +
                        "看它究竟记得你多少，你这一生就值多少。"
            )
        }

        view.rvSpeciesPainting.adapter = SpeciesPaintingAdapter(context!!, data)
        view.rvSpeciesPainting.isFocusable = false
    }

    override fun initData(view: View) {

    }


}