package com.lucianBen.baselibrary

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_common_title.view.*

/**
 *  Created by LucianBen on 2019/6/11
 *  Describe:   头部布局
 */

class HeaderBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    //是否显示"返回"图标
    private var isShowBack = true
    //Title文字
    private var titleText:String? = null
    //右侧文字
    private var rightText:String? = null

    init {
        //获取自定义属性
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        typedArray.recycle()
    }

    /*
        初始化视图
     */
    private fun initView() {
        View.inflate(context,R.layout.layout_common_title,this)

        ivLeftImage.visibility = if (isShowBack) View.VISIBLE else View.GONE

        //标题不为空，设置值
        titleText?.let {
            tvTitle.text = it
        }

        //右侧文字不为空，设置值
        rightText?.let {
            tvRightNext.text = it
            tvRightNext.visibility = View.VISIBLE
        }

        //返回图标默认实现（关闭Activity）
        ivLeftImage.setOnClickListener {
            if (context is Activity){
                (context as Activity).finish()
            }
        }

    }

    /*
        获取左侧视图
     */
    fun getLeftView(): ImageView {
        return ivLeftImage
    }

    /*
        获取右侧视图
     */
    fun getRightView(): TextView {
        return tvRightNext
    }

    /*
        获取右侧文字
     */
    fun getRightText():String{
        return tvRightNext.text.toString()
    }
}

