package com.alexgomes.redbag.custom

import android.content.Context
import android.graphics.Typeface


/**
 * Created by agomes on 9/8/18.
 */
class FontManager{
    companion object{
        var fontMap = hashMapOf<String,Typeface>()

        fun getFont(context: Context, fontName: String): Typeface? {
            return if (fontMap.containsKey(fontName)) {
                fontMap[fontName]
            } else {
                val tf = Typeface.createFromAsset(context.assets, "fonts/$fontName")
                fontMap[fontName] = tf
                tf
            }

        }

    }
}