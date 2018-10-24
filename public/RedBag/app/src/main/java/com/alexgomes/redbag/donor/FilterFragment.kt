package com.alexgomes.redbag.donor

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexgomes.redbag.BloodGroup
import com.alexgomes.redbag.R
import com.alexgomes.redbag.custom.BubbleFilterTextView
import kotlinx.android.synthetic.main.fragment_filter.*
import java.util.*


/**
 * Created by agomes on 10/13/18.
 */
class FilterFragment : Fragment() {

    interface OnFilterSet {
        fun filterSet()
    }

    lateinit var selectedFilter: ArrayList<String>
    lateinit var onFilterSet: OnFilterSet

    companion object {

        fun newInstance(selectedFilter: ArrayList<String>): FilterFragment {
            val args: Bundle = Bundle()
            args.putStringArrayList("selectedFilter", selectedFilter)

            val fragment = FilterFragment()
            fragment.arguments = args

            fragment.selectedFilter = args.getStringArrayList("selectedFilter")
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.v("==TAG==", "FilterFragment.onViewCreated " + selectedFilter.toString())

        close1.animate().alpha(1.0f).setStartDelay(300).setDuration(300).start()

        enumValues<BloodGroup>().forEach {
            val bubbleView = BubbleFilterTextView(context)
            bubbleView.text = it.value
            blood_group.addView(bubbleView)

            if (selectedFilter.contains(it.value)) {
                bubbleView.isSelected = true
            }

            bubbleView.setOnClickListener { view ->
                view.isSelected = !view.isSelected

                if (selectedFilter.contains(it.value)) {
                    selectedFilter.remove(it.value)
                } else {
                    selectedFilter.add(it.value)
                }
            }
        }

        close1.setOnClickListener { closeFilter() }
        close2.setOnClickListener { closeFilter() }

        btnSubmit.setOnClickListener {
            closeFilter()
            onFilterSet.filterSet()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFilterSet) {
            onFilterSet = context
        }
    }

    private fun closeFilter() {
        close1.alpha = 0.0f
        activity.onBackPressed()
    }

}