package com.alexgomes.redbag.networking

import com.alexgomes.redbag.networking.reqest.Request_Get_BloodModel
import com.alexgomes.redbag.networking.response.DonorLoginRegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

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

//    @POST("/postBloodRequest")
//    fun requestBlood(@Body requestGetBloodModel: Request_Get_BloodModel.Posts): Call<Void>

    @POST("/getBloodRequestList")
    fun getBloodRequestList(@QueryMap body: HashMap<String, Any>): Call<Request_Get_BloodModel>


}