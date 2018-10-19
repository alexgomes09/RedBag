package com.alexgomes.redbag.networking

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
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("updated_at") val updatedAt: String = "")