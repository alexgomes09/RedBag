package com.alexgomes.redbag.networking.reqest

/**
 * Created by agomes on 9/15/18.
 */
data class Request_Get_BloodModel(
        val name: String,
        val age: Int,
        val bloodGroup: String,
        val numberOfBags: Int,
        val address: String,
        val phoneNumber: String,
        val email: String
)