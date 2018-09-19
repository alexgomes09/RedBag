package com.alexgomes.redbag

/**
 * Created by agomes on 9/17/18.
 */
object PrefUtil {

    const val USER_FINISHED_ONBOARDING = "USER_FINISHED_ONBOARDING"
    const val USER_CREATED_DONOR_PROFILE = "USER_CREATED_DONOR_PROFILE"

    // boolean
    fun putBoolean(prefsKey: String, prefsValue: Boolean) {
        RedBagApplication.getPreferences().edit().putBoolean(prefsKey, prefsValue).apply()
    }

    // boolean
    fun getBoolean(prefsKey: String, defaultValue: Boolean) = RedBagApplication.getPreferences().getBoolean(prefsKey, defaultValue)

}