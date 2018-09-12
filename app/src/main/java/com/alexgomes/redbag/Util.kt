package com.alexgomes.redbag

import android.content.res.Resources


/**
 * Created by agomes on 9/11/18.
 */
class Util {
    companion object
}

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() //dp to px

