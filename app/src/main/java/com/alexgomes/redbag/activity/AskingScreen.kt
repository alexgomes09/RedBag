package com.alexgomes.redbag.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexgomes.redbag.PrefUtil
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

            if (PrefUtil.getBoolean(PrefUtil.USER_CREATED_DONOR_PROFILE, false)) {
                // go to recipient post list
            } else {
                startActivity(Intent(AskingScreen@this,DonorCreateProfileActivity::class.java))
            }

            overridePendingTransition(R.anim.slide_in_right,R.anim.scale_out)
        })

        btnRecipient.setOnClickListener({
            startActivity(Intent(AskingScreen@this,RecipientHomeScreen::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.scale_out)
        })
    }
}