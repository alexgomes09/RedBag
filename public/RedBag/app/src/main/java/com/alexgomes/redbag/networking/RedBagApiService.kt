package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.reqest.CreateProfileModel
import com.alexgomes.redbag.networking.reqest.Request_Get_BloodModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by agomes on 9/14/18.
 */
interface RedBagApiService {

    @POST("/createProfile")
    fun createUserProfile(@Body profileModel: CreateProfileModel): Call<Void>

    @POST("/postBloodRequest")
    fun requestBlood(@Body requestGetBloodModel: Request_Get_BloodModel): Call<Void>

    @GET("/getBloodRequest")
    fun getBloodRequest(@Body requestGetBloodModel: Request_Get_BloodModel): Call<Void>



}