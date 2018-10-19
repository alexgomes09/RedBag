package com.alexgomes.redbag.donor

import android.support.v7.app.AppCompatActivity


/**
 * Created by agomes on 9/12/18.
 */
class DonorCreateProfileActivity : AppCompatActivity() {

//    private var previousAgeSelected = 0
//    private var previousBloodGroupSelected = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_donor_home_screen)
//
//        etAge.setOnClickListener {
//            Util.hideKeyboard(etAge)
//            showAgeDialog()
//        }
//
//        etBloodGroup.setOnClickListener {
//            Util.hideKeyboard(etBloodGroup)
//            showBloodGroupDialog()
//        }
//
//        btnSubmit.setOnClickListener {
//            Util.hideKeyboard(btnSubmit)
//
//            when {
//                TextUtils.isEmpty(etName.text.toString().trim()) -> {
//                    etName.error = "Name field is required"
//                    return@setOnClickListener
//                }
//                TextUtils.isEmpty(etAge.text.toString().trim()) -> {
//                    etAge.error = "Age field is required"
//                    return@setOnClickListener
//                }
//                TextUtils.isEmpty(etBloodGroup.text.toString().trim()) -> {
//                    etBloodGroup.error = "Blood group is required"
//                    return@setOnClickListener
//                }
//                TextUtils.isEmpty(etEmail.text.toString().trim()) && TextUtils.isEmpty(etPhoneNumber.text.toString().trim()) -> {
//                    Util.showToast(this@DonorCreateProfileActivity, "At least one contact information required")
//                    return@setOnClickListener
//                }
//                !TextUtils.isEmpty(etEmail.text.toString()) && !Util.isValidEmail(etEmail.text.toString()) -> {
//                    etEmail.error = "Valid email required"
//                    return@setOnClickListener
//                }
//            }
//
//            val profile = DonorModel("a@a.com", "a")
//
//            RestAdapter.registerWithEmail(profile, object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    response.isSuccessful.let {
//                        Util.showToast(this@DonorCreateProfileActivity, "Create Profile Success")
//                        PrefUtil.putBoolean(PrefUtil.USER_CREATED_DONOR_PROFILE,true)
//                        startActivity(Intent(this@DonorCreateProfileActivity, BloodRequestListActivity::class.java))
//                        finish()
//                    }
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    call.cancel()
//                    Util.showToast(this@DonorCreateProfileActivity, t.localizedMessage)
//                    Log.v("==TAG==", "DonorCreateProfileActivity.onFailure " + t.localizedMessage)
//                }
//            })
//        }
//    }
//
//
//
//    private fun showAgeDialog() {
//        val ageDialog = AlertDialog.Builder(this@DonorCreateProfileActivity)
//        ageDialog.setTitle("Select Your Age")
//
//        val arrayAdapter = ArrayAdapter<Int>(this@DonorCreateProfileActivity, android.R.layout.simple_list_item_single_choice)
//        for (age in Util.minAgeToDonateBlood..Util.maxAgeToDonateBlood) {
//            arrayAdapter.add(age)
//        }
//
//        ageDialog.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
//
//        ageDialog.setSingleChoiceItems(arrayAdapter, previousAgeSelected, DialogInterface.OnClickListener { dialog, which ->
//            val ageSelected = arrayAdapter.getItem(which)
//            etAge.setText(ageSelected.toString())
//            previousAgeSelected = which
//            etAge.error = null
//            dialog.dismiss()
//        })
//
//        ageDialog.show()
//    }
//
//    private fun showBloodGroupDialog() {
//        val bloodGroupDialog = AlertDialog.Builder(this@DonorCreateProfileActivity)
//        bloodGroupDialog.setTitle("Select Your Blood Group")
//
//        val arrayAdapter = ArrayAdapter<String>(this@DonorCreateProfileActivity, android.R.layout.simple_list_item_single_choice)
//
//        enumValues<BloodGroup>().forEach { arrayAdapter.add(it.value) }
//
//        bloodGroupDialog.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
//
//        bloodGroupDialog.setSingleChoiceItems(arrayAdapter, previousBloodGroupSelected, DialogInterface.OnClickListener { dialog, which ->
//            val bloodGroupSelected = arrayAdapter.getItem(which)
//            etBloodGroup.setText(bloodGroupSelected.toString())
//            previousBloodGroupSelected = which
//            etBloodGroup.error = null
//            dialog.dismiss()
//        })
//
//        bloodGroupDialog.show()
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
//    }
}