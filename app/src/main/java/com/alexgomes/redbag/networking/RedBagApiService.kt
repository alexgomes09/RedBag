package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.reqest.CreateProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by agomes on 9/14/18.
 */
interface RedBagApiService {

    @POST("/zeeu3mze")
    fun createUserProfile(@Body profileModel: CreateProfileModel): Call<Void>

}