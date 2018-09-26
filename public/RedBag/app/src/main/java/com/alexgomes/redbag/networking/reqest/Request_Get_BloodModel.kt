package com.alexgomes.redbag.networking.reqest

import com.google.gson.annotations.SerializedName

/**
 * Created by agomes on 9/15/18.
 */
class Request_Get_BloodModel {

    val success: String = ""
    val message: String = ""
    val posts: MutableList<Posts> = mutableListOf()

    class Posts(
            var name: String,
            var age: Int,
            var bloodGroup: String,
            var numberOfBags: Int,
            var address: String,
            var phoneNumber: String,
            var email: String,
            @SerializedName("created_at")
            var postedTime: String
    )


}