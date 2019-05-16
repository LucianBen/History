package com.lx.history.activity

import android.content.Intent
import com.lx.history.R
import com.lx.history.base.BaseActivity

class WelcomeActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_welcome

    override fun initView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}
