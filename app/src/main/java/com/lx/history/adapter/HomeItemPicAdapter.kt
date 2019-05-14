package com.lx.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lx.history.R

class HomeItemPicAdapter(private val context: Context, private val pics: List<String>) :
    RecyclerView.Adapter<HomeItemPicAdapter.HomeItemPicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemPicViewHolder =
        HomeItemPicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_single_pic, parent, false))

    override fun getItemCount(): Int = pics.size

    override fun onBindViewHolder(holder: HomeItemPicViewHolder, position: Int) {
        Glide.with(context)
            .load(pics[position])
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .into(holder.ivPic)
    }


    inner class HomeItemPicViewHolder(picView: View) : RecyclerView.ViewHolder(picView) {
        internal var ivPic: ImageView = picView.findViewById(R.id.iv_pic)
    }

}