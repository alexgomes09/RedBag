package com.alexgomes.redbag

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


/**
 * Created by agomes on 9/11/18.
 */

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() //dp to px

enum class BloodGroup(val value: String) {
    APOSITIVE("A+"),
    ANEGATIVE("A-"),
    BPOSITIVE("B+"),
    BNEGATIVE("B-"),
    ABPOSITIVE("AB+"),
    ABNEGATIVE("AB-"),
    OPOSITIVE("O+"),
    ONEGATIVE("O-"),
}

class Util {
    companion object {
        val networkTimeOut = 30L
        val BASE_URL: String = "http://localhost:8080"
        val minAgeToDonateBlood = 17
        val maxAgeToDonateBlood = 99

        fun checkForInternet(context: Context): Boolean {
            fun hasNetworkConnectivity(context: Context): Boolean {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }

            if (!hasNetworkConnectivity(context)) {
                Toast.makeText(context, context.getString(R.string.no_connectivity_message), Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun hideKeyboard(view: View) {
            //Pass any view
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun getAndroidUniqueId(): String {
            val androidId = Settings.Secure.getString(RedBagApplication.applicationContext().contentResolver, Settings.Secure.ANDROID_ID)
            return androidId
        }

        fun isValidEmail(email: String): Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}


