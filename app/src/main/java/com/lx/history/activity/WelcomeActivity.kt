package com.lx.history.activity

import android.content.Intent
import com.lucianBen.baselibrary.BaseActivity
import com.lx.history.R

class WelcomeActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_welcome

    override fun initView() {
        startActivity(Intent(this, MainActivity::class.java))
        removeActivity()
    }

    override fun initData() {

    }

}
