package com.alexgomes.redbag.networking

import com.google.gson.annotations.SerializedName

/**
 * Created by agomes on 9/15/18.
 */
data class DonorModel(
        var emailAddress: String = "",
        var password: String = "",
        var name: String = "",
        var age: Int = -1,
        var bloodGroup: String = "",
        var phoneNumber: String = "",
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("updated_at") val updatedAt: String = "")