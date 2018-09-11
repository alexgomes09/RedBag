package com.alexgomes.redbag.custom

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import java.lang.reflect.Type


/**
 * Created by agomes on 9/8/18.
 */
class FontManager{
    companion object{
        var fontMap = hashMapOf<String,Typeface>()

        fun getFont(context: Context, fontName: String): Typeface? {
            return if (fontMap.containsKey(fontName)) {
                Log.v("==TAG==", "FontManager.getFont getting font from map");
                fontMap[fontName]
            } else {
                val tf = Typeface.createFromAsset(context.assets, "fonts/$fontName")
                fontMap[fontName] = tf
                Log.v("==TAG==", "FontManager.getFont created font in map");
                tf
            }

        }

    }
}