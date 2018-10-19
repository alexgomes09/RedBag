package com.alexgomes.redbag.custom

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.TypedValue
import com.alexgomes.redbag.R
import com.alexgomes.redbag.dp


/**
 * Created by agomes on 10/14/18.
 */
class BubbleFilterTextView : CustomTextView {

    private var cornerRadius = 20.dp
    private val paddingLeftRight = 17.dp
    private val paddingTopBottom = 5.dp
    private val states = arrayOf(intArrayOf(-android.R.attr.state_selected), intArrayOf(android.R.attr.state_selected))
    private val colors = intArrayOf(Color.BLACK, Color.WHITE)

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        includeFontPadding = false

        ViewCompat.setElevation(this, 10.0f)

        setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)

        val a = context.obtainStyledAttributes(attrs, R.styleable.BubbleFilterTextView)
        cornerRadius = a.getDimensionPixelSize(R.styleable.BubbleFilterTextView_bubble_radius, cornerRadius)
        a.recycle()

        background = ContextCompat.getDrawable(context, R.drawable.selector_bubble)

        setTextColor(ColorStateList(states, colors))

        setOnClickListener {
            this.isSelected = !isSelected
        }
    }
}