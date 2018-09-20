package com.alexgomes.redbag.networking.reqest

/**
 * Created by agomes on 9/15/18.
 */
data class CreateProfileModel(
        val deviceId: String,
        val name: String,
        val age: Int,
        val bloodGroup: String,
        val phoneNumber: String,
        val email: String
)