package com.lx.history.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected open val layoutId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        initView(view)
        initData(view)
        return view
    }


    abstract fun initView(view: View)

    abstract fun initData(view: View)

    protected open fun jumpPage(clazz: Class<*>) {
        startActivity(Intent(activity, clazz))
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}