package com.nikolaenko.andrew.warden

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this).asGif().load(R.drawable.warden_splash).into(imageLogo)

        val mHandler = Handler()
        mHandler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 3000L)
    }
}
