package com.alexgomes.redbag.donor

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexgomes.redbag.BloodGroup
import com.alexgomes.redbag.R
import com.alexgomes.redbag.custom.BubbleFilterTextView
import kotlinx.android.synthetic.main.fragment_filter.*
import java.util.logging.Filter
import android.view.animation.AccelerateDecelerateInterpolator


/**
 * Created by agomes on 10/13/18.
 */
class FilterFragment : Fragment() {

    companion object {
        private const val ARG_CAUGHT = "myFragment_caught"

        fun newInstance(): FilterFragment {
//            val args: Bundle = Bundle()
//            args.putSerializable(ARG_CAUGHT, caught)

            val fragment = FilterFragment()
//            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        close1.animate().alpha(1.0f).setStartDelay(300).setDuration(300).start()

        enumValues<BloodGroup>().forEach {
            val bubbleView = BubbleFilterTextView(context)
            bubbleView.text = it.value
            blood_group.addView(bubbleView)
        }

        close1.setOnClickListener { closeFilter() }
        close2.setOnClickListener { closeFilter() }
    }


    private fun closeFilter(){
        close1.alpha = 0.0f
        activity.onBackPressed()
    }

}