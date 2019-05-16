package com.lx.history.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lx.history.R
import com.lx.history.base.BaseFragment
import com.lx.history.control.TabLayoutMediator
import kotlinx.android.synthetic.main.layout_mainview_species.view.*

class SpeciesFragment : BaseFragment() {
    override val layoutId: Int = R.layout.layout_mainview_species

    val tabName = arrayListOf("画作", "古籍")

    override fun initView(view: View) {
        view.speciesViewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItem(position: Int): Fragment =
                when (position) {
                    0 -> SpeciesPaintingFragment.create()
                    else -> SpeciesBooksFragment.create()
                }

            override fun getItemCount(): Int = tabName.size

        }

        TabLayoutMediator(view.speciesTablayout, view.speciesViewpager2) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }

    override fun initData(view: View) {

    }

}