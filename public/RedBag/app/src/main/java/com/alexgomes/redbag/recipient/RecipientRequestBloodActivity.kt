package com.alexgomes.redbag.recipient

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.alexgomes.redbag.R
import com.alexgomes.redbag.networking.RestAdapter
import com.alexgomes.redbag.networking.generic.BaseModel
import com.alexgomes.redbag.networking.generic.Location
import com.alexgomes.redbag.networking.generic.PostModel
import com.alexgomes.redbag.util.BloodGroup
import com.alexgomes.redbag.util.Util
import kotlinx.android.synthetic.main.activity_recipient_request_blood.*
import kotlinx.android.synthetic.main.partial_appbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by agomes on 9/16/18.
 */
class RecipientRequestBloodActivity : AppCompatActivity() {

    private var previousBagSelected = 0
    private var previousAgeSelected = 0
    private var previousBloodGroupSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipient_request_blood)

        appbar_title.text = "Request blood"

        etAge.setOnClickListener {
            Util.hideKeyboard(etAge)
            showAgeDialog()
        }

        etBloodGroup.setOnClickListener {
            Util.hideKeyboard(etBloodGroup)
            showBloodGroupDialog()
        }

        etNumberOfBags.setOnClickListener {
            Util.hideKeyboard(etBloodGroup)
            showNumberOfBagsDialog()
        }

        btnSubmit.setOnClickListener {
            Util.hideKeyboard(btnSubmit)

            when {
                etName.getText().trim().isEmpty() -> {
                    etName.requestFocus()
                    etName.setError("Name is required")
                    return@setOnClickListener
                }
                etAge.getText().trim().isEmpty() -> {
                    etAge.setError("Age is required")
                    return@setOnClickListener
                }
                etBloodGroup.getText().trim().isEmpty() -> {
                    etBloodGroup.setError("Blood group is required")
                    return@setOnClickListener
                }
                etNumberOfBags.getText().trim().isEmpty() -> {
                    etNumberOfBags.setError("Number of bags field is required")
                    return@setOnClickListener
                }
                etAddress.getText().trim().isEmpty() -> {
                    etAddress.requestFocus()
                    etAddress.setError("Address is required")
                    return@setOnClickListener
                }
                etPhoneNumber.getText().trim().isEmpty() && etEmail.getText().trim().isEmpty() -> {
                    Util.showToast(this@RecipientRequestBloodActivity, "At least one contact information required")
                    return@setOnClickListener
                }
                etEmail.getText().trim().isNotEmpty() && !Util.isValidEmail(etEmail.getText().trim()) -> {
                    etEmail.requestFocus()
                    etEmail.setError("Valid email required")
                    return@setOnClickListener
                }
            }

            val requestPost = PostModel(
                    etBloodGroup.getText(),
                    previousBagSelected,
                    etPhoneNumber.getText(),
                    etName.getText(),
                    etAddress.getText(),
                    etEmail.getText(),
                    etAge.getText().toInt(), Location(mutableListOf(-79.273838,43.744313)))

            RestAdapter.requestBlood(requestPost, object : Callback<BaseModel> {
                override fun onResponse(call: Call<BaseModel>, response: Response<BaseModel>) {
                    response.isSuccessful.let {
                        Util.showToast(this@RecipientRequestBloodActivity, response.body()!!.message)
                    }
                }

                override fun onFailure(call: Call<BaseModel>, t: Throwable) {
                    call.cancel()
                    Util.showToast(this@RecipientRequestBloodActivity, t.localizedMessage)
                    Log.v("==TAG==", "RecipientRequestBloodActivity.onFailure " +t.localizedMessage)
                }
            })
        }
    }

    private fun showNumberOfBagsDialog() {
        val numberOfBags = AlertDialog.Builder(this@RecipientRequestBloodActivity)
        numberOfBags.setTitle("Amount of blood needed")

        val arrayAdapter = ArrayAdapter<String>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)
        for (bloodBag in 1..12) {
            arrayAdapter.add(resources.getQuantityString(R.plurals.bloodBag, bloodBag, bloodBag))
        }

        numberOfBags.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        numberOfBags.setSingleChoiceItems(arrayAdapter, previousBagSelected, DialogInterface.OnClickListener { dialog, which ->
            val numberOfBagsSelected = arrayAdapter.getItem(which)
            etNumberOfBags.setText(numberOfBagsSelected.toString())
            previousBagSelected = which
            etNumberOfBags.setError(null)
            dialog.dismiss()
        })

        numberOfBags.show()
    }

    private fun showAgeDialog() {
        val ageDialog = AlertDialog.Builder(this@RecipientRequestBloodActivity)
        ageDialog.setTitle("Select Patient Age")

        val arrayAdapter = ArrayAdapter<Int>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)
        for (age in Util.minAgeToDonateBlood..Util.maxAgeToDonateBlood) {
            arrayAdapter.add(age)
        }

        ageDialog.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        ageDialog.setSingleChoiceItems(arrayAdapter, previousAgeSelected, DialogInterface.OnClickListener { dialog, which ->
            val ageSelected = arrayAdapter.getItem(which)
            etAge.setText(ageSelected.toString())
            previousAgeSelected = which
            etAge.setError(null)
            dialog.dismiss()
        })

        ageDialog.show()
    }

    private fun showBloodGroupDialog() {
        val bloodGroupDialog = AlertDialog.Builder(this@RecipientRequestBloodActivity)
        bloodGroupDialog.setTitle("Select Patient Blood Group")

        val arrayAdapter = ArrayAdapter<String>(this@RecipientRequestBloodActivity, android.R.layout.simple_list_item_single_choice)

        enumValues<BloodGroup>().forEach { arrayAdapter.add(it.value) }

        bloodGroupDialog.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        bloodGroupDialog.setSingleChoiceItems(arrayAdapter, previousBloodGroupSelected, DialogInterface.OnClickListener { dialog, which ->
            val bloodGroupSelected = arrayAdapter.getItem(which)
            etBloodGroup.setText(bloodGroupSelected.toString())
            previousBloodGroupSelected = which
            etBloodGroup.setError(null)
            dialog.dismiss()
        })

        bloodGroupDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.scale_in, R.anim.slide_out_right)
    }
}