package com.lx.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lx.history.R

/**
 *  Created by LucianBen on 2019/5/16
 *  Describe: 分类页面-画作fragment的adapter
 */


class SpeciesPaintingAdapter(val context: Context, private val data: List<String>) :
    RecyclerView.Adapter<SpeciesPaintingAdapter.SpeciedPaintingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciedPaintingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_species_painting, parent, false)
        return SpeciedPaintingViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SpeciedPaintingViewHolder, position: Int) {
        holder.tvPaintingDate.text = data[position]

    }

    inner class SpeciedPaintingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvPaintingDate: TextView = itemView.findViewById(R.id.tvPaintingDate)
    }
}