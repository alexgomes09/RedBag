package com.alexgomes.redbag.custom

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.alexgomes.redbag.R


/**
 * Created by agomes on 9/8/18.
 */
class CustomTextView : AppCompatTextView {

    private lateinit var fontMap: Map<String, Typeface>

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val fontName = typedArray.getString(R.styleable.CustomTextView_custom_font)
        typedArray.recycle()

        if (fontName != null) {
            typeface = FontManager.getFont(context, fontName)
        } else {
            typeface = FontManager.getFont(context, context.getString(R.string.font_regular))
        }

        typeface = FontManager.getFont(context, context.getString(R.string.font_regular))
    }
}