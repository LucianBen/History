package com.lx.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ViewpagerView(layoutInflater: LayoutInflater,container:ViewGroup) {
val pagerview: View =layoutInflater.inflate(R.layout.layout_mainview_timeline,container,false)

    private val context:TextView
    init {
        context=pagerview.findViewById(R.id.tv_name)
    }
    fun bind(tabName: String){
        context.text=tabName
    }

}