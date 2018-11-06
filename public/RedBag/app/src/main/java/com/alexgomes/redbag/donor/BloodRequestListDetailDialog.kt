package com.alexgomes.redbag.donor

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatDialogFragment
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.alexgomes.redbag.R
import com.alexgomes.redbag.networking.generic.PostModel
import com.alexgomes.redbag.util.Util
import kotlinx.android.synthetic.main.dialog_blood_request_list_detail.*


/**
 * Created by agomes on 11/6/18.
 */
class BloodRequestListDetailDialog : AppCompatDialogFragment() {

    private lateinit var bloodRequestItem: PostModel

    companion object {
        fun newInstance(bloodRequestItem: PostModel): BloodRequestListDetailDialog {
            val fragmentExample = BloodRequestListDetailDialog()

            val bundle = Bundle()
            bundle.putParcelable("bloodRequestModel", bloodRequestItem)
            fragmentExample.arguments = bundle

            return fragmentExample
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_blood_request_list_detail, container, false)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(0))
        dialog.window.setDimAmount(Util.dialogDimAmount)
        setBackgroundGradient(view)

        bloodRequestItem = arguments.getParcelable<PostModel>("bloodRequestModel")

        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95f).toInt() //span 90% of screen width
        val height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        dialog.window!!.setLayout(width, height)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recipientName.text = bloodRequestItem.name
        recipientBloodRequire.text = context.resources.getQuantityString(R.plurals.bloodBag, bloodRequestItem.numberOfBags, bloodRequestItem.numberOfBags) + " of blood"
        recipientAge.text = "${bloodRequestItem.age} years old"

        if (!bloodRequestItem.address.isNullOrEmpty()) {
            t4.visibility = View.VISIBLE
            recipientAddress.visibility = View.VISIBLE
            recipientAddress.text = bloodRequestItem.address
        }

        if (!bloodRequestItem.phoneNumber.isNullOrEmpty()) {
            t5.visibility = View.VISIBLE
            recipientPhoneNumber.visibility = View.VISIBLE
            recipientPhoneNumber.text = bloodRequestItem.phoneNumber
            Linkify.addLinks(recipientPhoneNumber, Linkify.PHONE_NUMBERS)
        }

        if (!bloodRequestItem.email.isNullOrEmpty()) {
            t6.visibility = View.VISIBLE
            recipientEmail.visibility = View.VISIBLE
            recipientEmail.text = bloodRequestItem.email
            Linkify.addLinks(recipientEmail, Linkify.EMAIL_ADDRESSES)
        }
    }


    private fun setBackgroundGradient(view: View) {
        val gd = GradientDrawable()
        gd.setColor(Color.WHITE)
        gd.cornerRadius = resources.getDimension(R.dimen.rounded_corner)
        view.background = gd
    }


}