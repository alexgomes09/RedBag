package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.generic.PostModel
import com.alexgomes.redbag.networking.reqest.BloodRequestPosts
import com.alexgomes.redbag.networking.response.DonorLoginRegisterResponse
import retrofit2.Call
import retrofit2.http.*

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
                            @Query("bloodGroup",encoded = false) list: ArrayList<String>,
                            @Query("sort") sort: String): Call<BloodRequestPosts>

    @POST("/postBloodRequest")
    fun requestBlood(@Body requestPosts: PostModel): Call<Void>
}