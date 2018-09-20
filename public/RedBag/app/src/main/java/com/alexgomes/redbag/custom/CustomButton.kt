package com.alexgomes.redbag.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.alexgomes.redbag.R
import com.alexgomes.redbag.dp


/**
 * Created by agomes on 9/11/18.
 */
class CustomButton : AppCompatButton {

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
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton) as TypedArray
            val fontName = a.getString(R.styleable.CustomButton_custom_font)
            a.recycle()

            if (fontName != null) {
                typeface = FontManager.getFont(context, fontName)
            } else {
                typeface = FontManager.getFont(context, context.getString(R.string.font_bold))
            }
        } else {
            typeface = FontManager.getFont(context, context.getString(R.string.font_bold))
        }

        includeFontPadding = false

        DoRoundedCorner()
    }

    private fun DoRoundedCorner(){
        val drawable = GradientDrawable()
        drawable.cornerRadius = 8.dp.toFloat()
        if(background is ColorDrawable){
            drawable.setColor((background as ColorDrawable).color)
        }
        background = drawable
    }
}