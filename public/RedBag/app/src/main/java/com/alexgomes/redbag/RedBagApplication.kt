package com.alexgomes.redbag

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by agomes on 9/15/18.
 */
class RedBagApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: RedBagApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getPreferences(): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(instance)
        }
    }

}