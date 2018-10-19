package com.alexgomes.redbag.recipient

import android.support.v7.app.AppCompatActivity

/**
 * Created by agomes on 9/16/18.
 */
class RecipientRequestBloodActivity : AppCompatActivity() {
//
//    private var previousBagSelected = 0
//    private var previousAgeSelected = 0
//    private var previousBloodGroupSelected = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_recipient_request_blood)
//
//        appbar_title.text = "Request blood"
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
//        etNumberOfBags.setOnClickListener {
//            Util.hideKeyboard(etBloodGroup)
//            showNumberOfBagsDialog()
//        }
//
//        btnSubmit.setOnClickListener {
//            Util.hideKeyboard(btnSubmit)
//
//            when {
//                etName.text.toString().trim().isEmpty() -> {
//                    etName.error = "Name field is required"
//                    return@setOnClickListener
//                }
//                etAge.text.toString().trim().isEmpty() -> {
//                    etAge.error = "Age field is required"
//                    return@setOnClickListener
//                }
//                etBloodGroup.text.toString().trim().isEmpty() -> {
//                    etBloodGroup.error = "Blood group is required"
//                    return@setOnClickListener
//                }
//                etNumberOfBags.text.toString().trim().isEmpty() -> {
//                    etNumberOfBags.error = "Number of bags field is required"
//                    return@setOnClickListener
//                }
//                etEmail.text.toString().trim().isEmpty() && etPhoneNumber.text.toString().trim().isEmpty() -> {
//                    Util.showToast(this@RecipientRequestBloodActivity, "At least one contact information required")
//                    return@setOnClickListener
//                }
//                etEmail.text.toString().trim().isNotEmpty() && !Util.isValidEmail(etEmail.text.toString().trim()) -> {
//                    etEmail.error = "Valid email required"
//                    return@setOnClickListener
//                }
//            }
//
//
//            val requestBloodModel = Request_Get_BloodModel.Posts(
//                    etName.text.toString(),
//                    etAge.text.toString().toInt(),
//                    etBloodGroup.text.toString(),
//                    previousBagSelected,
//                    etAddress.text.toString(),
//                    etPhoneNumber.text.toString(),
//                    etEmail.text.toString(),""
//            )
//
//
//            RestAdapter.requestBlood(requestBloodModel, object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    response.isSuccessful.let {
//                        Util.showToast(this@RecipientRequestBloodActivity, "Blood Request Success")
//                    }
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    call.cancel()
//                    Util.showToast(this@RecipientRequestBloodActivity, t.localizedMessage)
//                    Log.v("==TAG==", "RecipientRequestBloodActivity.onFailure " +t.localizedMessage)
//                }
//            })
//        }
//    }
//
//    private fun showNumberOfBagsDialog() {
//        val numberOfBags = AlertDialog.Builder(this@RecipientRequestBloodActivity)
//        numberOfBags.setTitle("Amount of blood needed")
//
//        val arrayAdapter = ArrayAdapter<String>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)
//        for (bloodBag in 1..12) {
//            arrayAdapter.add(resources.getQuantityString(R.plurals.bloodBag, bloodBag, bloodBag))
//        }
//
//        numberOfBags.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
//
//        numberOfBags.setSingleChoiceItems(arrayAdapter, previousBagSelected, DialogInterface.OnClickListener { dialog, which ->
//            val numberOfBagsSelected = arrayAdapter.getItem(which)
//            etNumberOfBags.setText(numberOfBagsSelected.toString())
//            previousBagSelected = which
//            etNumberOfBags.error = null
//            dialog.dismiss()
//        })
//
//        numberOfBags.show()
//    }
//
//    private fun showAgeDialog() {
//        val ageDialog = AlertDialog.Builder(this@RecipientRequestBloodActivity)
//        ageDialog.setTitle("Select Patient Age")
//
//        val arrayAdapter = ArrayAdapter<Int>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)
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
//        val bloodGroupDialog = AlertDialog.Builder(this@RecipientRequestBloodActivity)
//        bloodGroupDialog.setTitle("Select Patient Blood Group")
//
//        val arrayAdapter = ArrayAdapter<String>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)
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