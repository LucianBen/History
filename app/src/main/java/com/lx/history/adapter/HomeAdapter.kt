package com.lx.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lx.history.R

class HomeAdapter(private val context: Context, private val data: List<String>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mainview_timeline,parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.tvName.text=data[position]
    }


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvName: TextView = itemView.findViewById(R.id.tvItemName)
    }
}


