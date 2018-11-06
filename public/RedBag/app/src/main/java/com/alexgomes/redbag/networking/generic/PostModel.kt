package com.alexgomes.redbag.networking.generic
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by agomes on 9/15/18.
 */
@Parcelize
data class PostModel internal constructor(
        val bloodGroup: String = "",
        val numberOfBags: Int = -1,
        val phoneNumber: String = "",
        val name: String = "",
        val address: String = "",
        val emailAddress: String = "",
        val age: Int = -1,
        val location: Location,
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("updated_at") val updatedAt: String = "") : Parcelable

@Parcelize
class Location(private val coordinates: MutableList<Double>) : Parcelable {
    fun getLongitude(): Double {
        return coordinates[0]
    }

    fun getLatitude(): Double {
        return coordinates[1]
    }
}