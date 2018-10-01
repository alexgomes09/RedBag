package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.reqest.CreateProfileModel
import com.alexgomes.redbag.networking.reqest.Request_Get_BloodModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * Created by agomes on 9/14/18.
 */
interface RedBagApiService {

    @POST("/createProfile")
    fun createUserProfile(@Body profileModel: CreateProfileModel): Call<Void>

    @POST("/postBloodRequest")
    fun requestBlood(@Body requestGetBloodModel: Request_Get_BloodModel.Posts): Call<Void>

    @POST("/getBloodRequest")
    fun getBloodRequest(@QueryMap body: HashMap<String,String>): Call<Request_Get_BloodModel>


}