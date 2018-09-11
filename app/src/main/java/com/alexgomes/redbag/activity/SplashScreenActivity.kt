package com.alexgomes.redbag.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.alexgomes.redbag.R

/**
 * Created by agomes on 9/8/18.
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        Handler().postDelayed({
            val slideScreenActivity = Intent(this, SlideScreenActivity::class.java)
            startActivity(slideScreenActivity)
            finish()
        },3000L)
    }
}

