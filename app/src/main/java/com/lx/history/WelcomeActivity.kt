package com.lx.history

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        startActivity(Intent(this,MainActivity::class.java))
    }
}
