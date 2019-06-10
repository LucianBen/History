package com.lx.history.activity

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lx.common.BaseActivity
import com.lx.history.R
import com.lx.history.adapter.PaintingLongAdapter
import kotlinx.android.synthetic.main.activity_painting_long.*
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *  收藏画作界面
 *  Created by LucianBen on 2019/05/31.
 * */
class PaintingLongActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_painting_long

    private val data = listOf(
        "小白烧酒的故事", "武大郎卖鞋", "虾米也有春天", "白马跑步图",
        "春来秋去", "小兔开门图", "茶杯喝水图", "娃哈哈广告图", "傻逼直播图 C.D. 4500",
        "菊花爆开图 A.D. 2001", "世博会开幕式 A.D. 2010", "世界金融危机解决方案", "采购进行ing"
    )

    override fun initView() {
        tvTitle.text = "长幅画作"

        rv_painting_long.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_painting_long.itemAnimator = DefaultItemAnimator()
        rv_painting_long.adapter = PaintingLongAdapter(this, data)

    }

    override fun initData() {

    }


}
