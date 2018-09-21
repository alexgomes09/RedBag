package com.alexgomes.redbag.networking.reqest

import com.google.gson.annotations.SerializedName

/**
 * Created by agomes on 9/15/18.
 */
class Request_Get_BloodModel {

    val success: String = ""
    val message: String = ""
    val posts = mutableListOf<Posts>()

    class Posts{
        lateinit var name: String
        lateinit var age: Integer
        lateinit var bloodGroup: String
        lateinit var numberOfBags: Integer
        lateinit var address: String
        lateinit var phoneNumber: String
        lateinit var email: String
        @SerializedName("created_at")
        lateinit var posted: String
    }



}