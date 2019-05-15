package com.lx.history.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lx.history.MyApplication
import android.content.Intent



abstract class BaseActivity : AppCompatActivity() {

    protected open val layoutId: Int = 0
    private var application: MyApplication? = null
    private var context: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        initView()

//        if (application == null) {// 得到Application对象
//            application = getApplication() as MyApplication
//        }
        context = this// 把当前的上下文对象赋值给BaseActivity
//        addActivity()// 调用添加方法

    }

    protected abstract fun initView()


    // 添加Activity方法
    private fun addActivity() {
        context?.let { application!!.addActivity(it) }// 调用myApplication的添加Activity方法
    }

    //销毁当个Activity方法
    fun removeActivity() {
        context?.let { application!!.removeActivity(it) }// 调用myApplication的销毁单个Activity方法
    }

    //销毁所有Activity方法
    fun removeAllActivity() {
        application!!.removeALLActivity()// 调用myApplication的销毁所有Activity方法
    }

    /* 把Toast定义成一个方法  可以重复使用，使用时只需要传入需要提示的内容即可*/
    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


    protected fun readyGo(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

}


