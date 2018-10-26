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
import kotlin.collections.ArrayList


/**
 * Created by agomes on 10/13/18.
 */
class FilterFragment : Fragment() {

    interface OnFilterSet {
        fun filterSet(selectedSort: String)
    }

    lateinit var selectedFilter: ArrayList<String>
    lateinit var selectedSort: String
    private var onFilterSet: OnFilterSet? = null

    companion object {

        fun newInstance(selectedFilter: ArrayList<String>, filterSort: String): FilterFragment {
            val args: Bundle = Bundle()
            args.putStringArrayList("selectedFilter", selectedFilter)
            args.putString("selectedSort", filterSort)

            val fragment = FilterFragment()
            fragment.arguments = args

            selectedFilter.clear()
            fragment.selectedFilter = args.getStringArrayList("selectedFilter")
            fragment.selectedSort = args.getString("selectedSort")
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

        if (selectedSort.equals("desc")) {
            sort_new_to_old.isChecked = true
        } else {
            sort_old_to_new.isChecked = true
        }

        close1.setOnClickListener { closeFilter() }
        close2.setOnClickListener { closeFilter() }

        btnSubmit.setOnClickListener {
            if (sort_new_to_old.isChecked) {
                selectedSort = "desc"
            } else if (sort_old_to_new.isChecked) {
                selectedSort = "asc"
            }

            onFilterSet?.filterSet(selectedSort)
            closeFilter()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFilterSet) {
            onFilterSet = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onFilterSet = null
    }

    private fun closeFilter() {
        close1.alpha = 0.0f
        activity.onBackPressed()
    }
}