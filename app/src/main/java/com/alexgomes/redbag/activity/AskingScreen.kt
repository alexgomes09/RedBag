package com.alexgomes.redbag.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexgomes.redbag.R
import kotlinx.android.synthetic.main.activity_asking.*

/**
 * Created by agomes on 9/11/18.
 */
class AskingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asking)

        btnDonor.setOnClickListener({
            startActivity(Intent(AskingScreen@this,DonorHomeScreen::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        })

        btnRecipient.setOnClickListener({

        })
    }
}