package com.lx.historyworks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lx.historyworks.R

/**
 *  Created by LucianBen on 2019/5/31
 *  Describe: 长幅画作 Activity 的适配器
 */

class PaintingLongAdapter(val context: Context, private val data: List<String>) :
    RecyclerView.Adapter<PaintingLongAdapter.PaintingLongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintingLongViewHolder =
        PaintingLongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_painting_long, parent, false))


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PaintingLongViewHolder, position: Int) {
        holder.tvNameAndPaint.text = "\t\t" + data[position]
        val drawable=context.resources.getDrawable(R.mipmap.default_painting)
        drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
        holder.tvNameAndPaint.setCompoundDrawables(
            null, drawable
            , null, null
        )
    }


    inner class PaintingLongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvNameAndPaint = itemView.findViewById<TextView>(R.id.tvNameAndPaint)
    }
}

