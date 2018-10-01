package com.alexgomes.redbag.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.alexgomes.redbag.PrefUtil
import com.alexgomes.redbag.R

/**
 * Created by agomes on 9/8/18.
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            if (PrefUtil.getBoolean(PrefUtil.USER_FINISHED_ONBOARDING, false)) {
                gotoAskingScreen()
            } else {
                gotoSlideScreen()
            }

        }, 1000L)

    }

    fun gotoSlideScreen() {
        val slideScreenActivity = Intent(this, SlideScreenActivity::class.java)
        startActivity(slideScreenActivity)
        finish()
    }

    fun gotoAskingScreen() {
        val askingScreen = Intent(this, AskingScreen::class.java)
        startActivity(askingScreen)
        finish()
    }
}

