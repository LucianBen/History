package com.lx.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        holder.tvPaintingType.bringToFront()

        holder.tvPaintingDate.text = "—— 5月${29 - position}日 ——"
        holder.tvPaintingTitle.text = "第 $position 个标题"
        holder.tvPaintingAuthor.text = "$position"
        holder.tvPaintingContent.text = data[position]
        holder.tvPaintingType.text = "类型$position"

        Glide.with(context)
            .load("https://zp.meishuzuopin.net/wp-content/uploads/2018/10/%E7%BE%8E%E6%9C%AF%E4%BD%9C%E5%93%81Vmii1b.jpg")
            .error(R.drawable.vector_drawable_no_data).into(holder.ivPaintingImage)
    }

    inner class SpeciedPaintingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvPaintingDate: TextView = itemView.findViewById(R.id.tvPaintingDate)
        internal val tvPaintingTitle: TextView = itemView.findViewById(R.id.tvPaintingTitle)
        internal val tvPaintingAuthor: TextView = itemView.findViewById(R.id.tvPaintingAuthor)
        internal val tvPaintingContent: TextView = itemView.findViewById(R.id.tvPaintingContent)
        internal val tvPaintingType: TextView = itemView.findViewById(R.id.tvPaintingType)
        internal val ivPaintingImage: ImageView = itemView.findViewById(R.id.ivPaintingImage)
    }
}