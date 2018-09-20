package com.alexgomes.redbag.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.ViewGroup
import com.alexgomes.redbag.R
import com.alexgomes.redbag.dp


/**
 * Created by agomes on 9/12/18.
 */
class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val fontName = typedArray.getString(R.styleable.CustomTextView_custom_font)
        typedArray.recycle()

        typeface = if (fontName != null) {
            FontManager.getFont(context, fontName)
        } else {
            FontManager.getFont(context, context.getString(R.string.font_regular))
        }

        background = ContextCompat.getDrawable(context, R.drawable.et_background)

        setTextColor(ContextCompat.getColor(context,android.R.color.black))

        setSingleLine(true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val params = layoutParams as ViewGroup.MarginLayoutParams

        params.topMargin = params.topMargin + 10.dp
        params.bottomMargin = params.bottomMargin + 10.dp

        setPadding(10.dp, 8.dp, 10.dp, 8.dp)
    }
}