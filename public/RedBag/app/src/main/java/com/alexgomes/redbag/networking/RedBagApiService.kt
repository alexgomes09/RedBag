package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.reqest.BloodRequestPosts
import com.alexgomes.redbag.networking.response.DonorLoginRegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by agomes on 9/14/18.
 */
interface RedBagApiService {

    @FormUrlEncoded
    @POST("/registerWithEmail")
    fun registerWithEmail(@Field("emailAddress") emailAddress: String,
                          @Field("password") password: String,
                          @Field("bloodGroup") bloodGroup: String): Call<DonorLoginRegisterResponse>

    @FormUrlEncoded
    @POST("/loginWithEmail")
    fun loginWithEmail(
            @Field("emailAddress") emailAddress: String,
            @Field("password") password: String): Call<DonorLoginRegisterResponse>

    @POST("/getBloodRequestList")
    fun getBloodRequestList(@Query("skip") skip: String,
                            @Query("bloodGroup",encoded = false) list: ArrayList<String>): Call<BloodRequestPosts>

//    @POST("/postBloodRequest")
//    fun requestBlood(@Body requestGetBloodModel: BloodRequestPosts.Posts): Call<Void>

}