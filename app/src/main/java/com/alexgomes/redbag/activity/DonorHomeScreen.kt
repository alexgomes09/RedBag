package com.alexgomes.redbag.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alexgomes.redbag.R
import kotlinx.android.synthetic.main.activity_donor_home_screen.*
import kotlinx.android.synthetic.main.partial_appbar.*


/**
 * Created by agomes on 9/12/18.
 */
class DonorHomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_home_screen)

        appbar_title.text = "Create Profile"



        btnSubmit.setOnClickListener {
            Toast.makeText(DonorHomeScreen@this,"Submit Clicked",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
    }
}