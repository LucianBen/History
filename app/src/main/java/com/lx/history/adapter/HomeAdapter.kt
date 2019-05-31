package com.lx.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lx.history.R

class HomeAdapter(private val context: Context, private val data: List<String>, private val picsList: List<String>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mainview_timeline, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.tvTime.text = "B.C\n${data[position]}"
        holder.tvName.text = "这是历史的开始 ${data[position]}"
        holder.tvType.text = "历史${position + 1}"

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        holder.rvPic.layoutManager = linearLayoutManager
        holder.rvPic.itemAnimator = DefaultItemAnimator()

        val adapter = HomeItemPicAdapter(context, picsList)
        holder.rvPic.adapter = adapter

    }


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvTime: TextView = itemView.findViewById(R.id.tvItemTime)
        internal var tvName: TextView = itemView.findViewById(R.id.tvItemName)
        internal var tvType: TextView = itemView.findViewById(R.id.tvItemType)
        internal var rvPic: RecyclerView = itemView.findViewById(R.id.rvtemImage)
    }

}


