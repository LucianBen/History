package com.lx.history.fragment

import android.view.View
import com.lx.history.R
import com.lx.history.base.BaseFragment

class SpeciesPaintingFragment : BaseFragment() {

    companion object {
        fun create(): SpeciesPaintingFragment = SpeciesPaintingFragment()
    }

    override val layoutId: Int
        get() = R.layout.layout_species_painting

    override fun initView(view: View) {

    }

    override fun initData(view: View) {
    }

}