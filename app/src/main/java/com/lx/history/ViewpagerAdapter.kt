package com.lx.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewpagerAdapter : RecyclerView.Adapter<ViewpagerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewpagerHolder =
        ViewpagerHolder(ViewpagerView(LayoutInflater.from(parent.context), parent))

    override fun getItemCount(): Int = TabName.TABNAME.size

    override fun onBindViewHolder(holder: ViewpagerHolder, position: Int) = holder.binder(TabName.TABNAME[position])
}

class ViewpagerHolder internal constructor(private val view: ViewpagerView) :
    RecyclerView.ViewHolder(view.pagerview) {
    internal fun binder(tabName: String) {
        view.bind(tabName)
    }


}

