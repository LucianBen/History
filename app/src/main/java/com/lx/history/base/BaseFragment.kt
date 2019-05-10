package com.lx.history.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected open val layoutId: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        initView(view)
        return view
    }


    abstract fun initView(view: View)
//    protected open fun jumpPage(nextActivity: AppCompatActivity) {
//        startActivity(Intent(activity, nextActivity::class.java))
//    }
}