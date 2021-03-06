package com.alexgomes.redbag.onboarding

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.alexgomes.redbag.R
import com.alexgomes.redbag.donor.BloodRequestListActivity
import com.alexgomes.redbag.donor.DonorAuthenticationActivity
import com.alexgomes.redbag.recipient.RecipientRequestBloodActivity
import com.alexgomes.redbag.util.PrefUtil
import kotlinx.android.synthetic.main.activity_asking.*


/**
 * Created by agomes on 9/11/18.
 */
class AskingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asking)

        btnDonor.setOnClickListener({

            if (!TextUtils.isEmpty(PrefUtil.getString(PrefUtil.DONOR_TOKEN, ""))) {
                startActivity(Intent(this@AskingScreen, BloodRequestListActivity::class.java))
            } else {
                startActivity(Intent(this@AskingScreen, DonorAuthenticationActivity::class.java))
            }

            overridePendingTransition(R.anim.slide_in_right, R.anim.scale_out)
        })

        btnRecipient.setOnClickListener({
            startActivity(Intent(AskingScreen@ this, RecipientRequestBloodActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.scale_out)
        })

        developer_preview.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            RoadMapDialog.newInstance().show(ft, null)
        }
    }
}