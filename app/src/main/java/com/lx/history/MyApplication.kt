package com.lx.history

import android.app.Activity
import android.app.Application


class MyApplication : Application() {
    companion object {

        var instance: MyApplication? = null
    }

    private val allList: MutableList<Activity>? = null//用于存放所有启动的Activity的集合

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    /**
     * 添加Activity
     */

    fun addActivity(activity: Activity) {// 判断当前集合中不存在该Activity
        if (!allList!!.contains(activity))
            allList.add(activity)//把当前Activity添加到集合中
    }

    /**
     * 销毁单个Activity
     */
    fun removeActivity(activity: Activity) {
        if (allList!!.contains(activity)) {
            allList.remove(activity)//从集合中移除
            activity.finish()//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    fun removeALLActivity() {
        for (activity: Activity in this.allList!!) {//通过循环，把集合中的所有Activity销毁
            activity.finish()
        }
    }


}