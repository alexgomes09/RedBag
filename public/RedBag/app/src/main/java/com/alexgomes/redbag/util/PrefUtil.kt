package com.alexgomes.redbag.util

import com.alexgomes.redbag.RedBagApplication

/**
 * Created by agomes on 9/17/18.
 */
object PrefUtil {

    const val USER_FINISHED_ONBOARDING = "USER_FINISHED_ONBOARDING"
    const val DONOR_TOKEN = "DONOR_TOKEN"

    // put boolean
    fun putBoolean(prefsKey: String, prefsValue: Boolean) {
        RedBagApplication.getPreferences().edit().putBoolean(prefsKey, prefsValue).apply()
    }

    // get boolean
    fun getBoolean(prefsKey: String, defaultValue: Boolean) = RedBagApplication.getPreferences().getBoolean(prefsKey, defaultValue)


    // put string
    fun putString(prefsKey: String, prefsValue: String) {
        RedBagApplication.getPreferences().edit().putString(prefsKey, prefsValue).apply()
    }

    // get string
    fun getString(prefsKey: String, defaultValue: String) = RedBagApplication.getPreferences().getString(prefsKey, defaultValue)


}