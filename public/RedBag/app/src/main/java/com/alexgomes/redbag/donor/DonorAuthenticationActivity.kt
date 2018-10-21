package com.alexgomes.redbag.donor

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.alexgomes.redbag.BloodGroup
import com.alexgomes.redbag.PrefUtil
import com.alexgomes.redbag.R
import com.alexgomes.redbag.Util
import com.alexgomes.redbag.custom.BubbleFilterTextView
import com.alexgomes.redbag.custom.FlowLayout
import com.alexgomes.redbag.custom.LoadingDialog
import com.alexgomes.redbag.networking.RestAdapter
import com.alexgomes.redbag.networking.response.DonorLoginRegisterResponse
import kotlinx.android.synthetic.main.activity_donor_authenticate.*
import kotlinx.android.synthetic.main.partial_appbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by agomes on 10/18/18.
 */
class DonorAuthenticationActivity : AppCompatActivity() {

    private var bloodGroup: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_authenticate)

        appbar_title.text = "Authenticate"

        enumValues<BloodGroup>().forEach {
            val bubbleView = BubbleFilterTextView(this@DonorAuthenticationActivity)
            bubbleView.text = it.value
            blood_group_selection.addView(bubbleView)
            bubbleView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    (view.parent as FlowLayout).dispatchSetSelected(false)
                    view.isSelected = true
                    bloodGroup = it.value
                }

            })
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    register_section.visibility = View.GONE
                    login_section.visibility = View.VISIBLE
                    login_section.isFocusable = false
                    Util.hideKeyboard(login_section)
                } else if (tab?.position == 1) {
                    login_section.visibility = View.GONE
                    register_section.visibility = View.VISIBLE
                    register_section.isFocusable = false
                    Util.hideKeyboard(register_section)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        btnLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(etLoginEmail.getText()) || !Util.isValidEmail(etLoginEmail.getText()) -> {
                    etLoginEmail.requestFocus()
                    etLoginEmail.setError("Valid email required")
                    return@setOnClickListener
                }
                TextUtils.isEmpty(etLoginPassword.getText()) -> {
                    etLoginPassword.requestFocus()
                    etLoginPassword.setError("Password required")
                    return@setOnClickListener
                }
            }

            Util.hideKeyboard(btnLogin)

            val emailAddress = etLoginEmail.getText()
            val password = etLoginPassword.getText()

            LoadingDialog.getInstance().showDialog(this, "Authenticating...")
            RestAdapter.loginWithEmail(emailAddress, password, object : Callback<DonorLoginRegisterResponse> {
                override fun onResponse(call: Call<DonorLoginRegisterResponse>, response: Response<DonorLoginRegisterResponse>) {
                    LoadingDialog.getInstance().dismiss()

                    if (response.isSuccessful) {
                        PrefUtil.putString(PrefUtil.DONOR_TOKEN, response.body()!!.token)
                        startActivity(Intent(this@DonorAuthenticationActivity, BloodRequestListActivity::class.java))
                        finish()
                    } else {
                        Util.showToast(this@DonorAuthenticationActivity, RestAdapter.parseError(response).message)
                    }
                }

                override fun onFailure(call: Call<DonorLoginRegisterResponse>, t: Throwable) {
                    LoadingDialog.getInstance().dismiss()
                    call.cancel()
                    Util.showToast(this@DonorAuthenticationActivity, t.localizedMessage)
                }
            })
        }

        btnRegister.setOnClickListener {

            when {
                TextUtils.isEmpty(etLoginEmail.getText()) || !Util.isValidEmail(etLoginEmail.getText()) -> {
                    etRegisterEmail.setError("Valid email required")
                    return@setOnClickListener
                }
                TextUtils.isEmpty(etLoginPassword.getText()) -> {
                    etRegisterPassword.setError("Password required")
                    return@setOnClickListener
                }

                TextUtils.isEmpty(bloodGroup) -> {
                    Util.showToast(this, "Select your blood group")
                    return@setOnClickListener
                }
            }

            Util.hideKeyboard(btnLogin)

            val emailAddress = etLoginEmail.getText()
            val password = etLoginPassword.getText()

            LoadingDialog.getInstance().showDialog(this, "Registering...")
            RestAdapter.registerWithEmail(emailAddress, password, bloodGroup, object : Callback<DonorLoginRegisterResponse> {
                override fun onResponse(call: Call<DonorLoginRegisterResponse>, response: Response<DonorLoginRegisterResponse>) {
                    LoadingDialog.getInstance().dismiss()
                    response.isSuccessful.let {
                        Util.showToast(this@DonorAuthenticationActivity, "Create Profile Success")
                        PrefUtil.putString(PrefUtil.USER_CREATED_DONOR_PROFILE, response.body()!!.token)
                        startActivity(Intent(this@DonorAuthenticationActivity, BloodRequestListActivity::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<DonorLoginRegisterResponse>, t: Throwable) {
                    LoadingDialog.getInstance().dismiss()
                    call.cancel()
                    Util.showToast(this@DonorAuthenticationActivity, t.localizedMessage)
                }
            })
        }
    }
}