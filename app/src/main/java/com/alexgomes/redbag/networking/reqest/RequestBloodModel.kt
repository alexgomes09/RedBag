package com.alexgomes.redbag.networking.reqest

/**
 * Created by agomes on 9/15/18.
 */
data class RequestBloodModel(
        val bloodGroup: String,
        val numberOfBags: Int,
        val phoneNumber: String,
        val name: String,
        val address: String,
        val email: String,
        val city: String,
        val age: Int
)