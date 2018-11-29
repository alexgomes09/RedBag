package com.alexgomes.redbag.onboarding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.alexgomes.redbag.R
import com.alexgomes.redbag.util.Util

/**
 * Created by agomes on 11/28/18.
 */
class RoadMapDialog : AppCompatDialogFragment() {

    companion object {
        fun newInstance(): RoadMapDialog{
            val roadMapDialog = RoadMapDialog()

            val bundle = Bundle()
            roadMapDialog.arguments = bundle

            return roadMapDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_roadmap, container, false)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(0))
        dialog.window.setDimAmount(Util.dialogDimAmount)
        setBackgroundGradient(view)
        return view
    }


    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95f).toInt() //span 95% of screen width
        val height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        dialog.window!!.setLayout(width, height)
    }

    private fun setBackgroundGradient(view: View) {
        val gd = GradientDrawable()
        gd.setColor(Color.WHITE)
        gd.cornerRadius = resources.getDimension(R.dimen.rounded_corner)
        view.background = gd
    }
}