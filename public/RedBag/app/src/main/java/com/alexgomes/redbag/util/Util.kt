package com.alexgomes.redbag.util

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.alexgomes.redbag.R


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
        const val MAX_RESPONSE_LIMIT = 30
        const val networkTimeOut = 30L
        const val BASE_URL: String = "http://localhost:8080/" //"https://red-bag.herokuapp.com/"
        const val minAgeToDonateBlood = 17
        const val maxAgeToDonateBlood = 99
        const val dialogDimAmount = 0.7f


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

        fun isValidEmail(email: String): Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}


