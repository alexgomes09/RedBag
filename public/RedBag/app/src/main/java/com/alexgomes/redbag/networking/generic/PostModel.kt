package com.alexgomes.redbag.networking.generic

import com.google.gson.annotations.SerializedName

/**
 * Created by agomes on 9/15/18.
 */
data class PostModel(
        val bloodGroup: String = "",
        val numberOfBags: Int= -1,
        val phoneNumber: String = "",
        val name: String = "",
        val address: String = "",
        val emailAddress: String = "",
        val age: Int = -1,
        val location: Location,
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("updated_at") val updatedAt: String = "")

data class Location(val coordinates: MutableList<Double>)