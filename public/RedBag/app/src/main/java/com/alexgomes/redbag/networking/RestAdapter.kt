package com.alexgomes.redbag.networking

import com.alexgomes.redbag.BuildConfig
import com.alexgomes.redbag.RedBagApplication
import com.alexgomes.redbag.Util
import com.alexgomes.redbag.networking.response.DonorLoginRegisterResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by agomes on 9/14/18.
 */
object RestAdapter {

    private var redBagApiService: RedBagApiService

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(Util.networkTimeOut, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(Util.networkTimeOut, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(Util.networkTimeOut, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()

        redBagApiService = retrofit.create(RedBagApiService::class.java)
    }

    fun registerWithEmail(emailAddress: String, password: String, bloodGroup: String, onResponseListener: Callback<DonorLoginRegisterResponse>) {
        //we check internet connection before making every network call
        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
        redBagApiService.registerWithEmail(emailAddress, password, bloodGroup).enqueue(onResponseListener)
    }

    fun loginWithEmail(emailAddress: String, password: String, onResponseListener: Callback<DonorLoginRegisterResponse>) {
        //we check internet connection before making every network call
        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
        redBagApiService.loginWithEmail(emailAddress, password).enqueue(onResponseListener)
    }

//    fun requestBlood(requestGetBloodModel: Request_Get_BloodModel.Posts, onResponseListener: Callback<Void>) {
//        we check internet connection before making every network call
//        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
//        redBagApiService.requestBlood(requestGetBloodModel).enqueue(onResponseListener)
//    }

//    fun getBloodRequest(body: HashMap<String, Any>, onResponseListener: Callback<Request_Get_BloodModel>) {
//        //we check internet connection before making every network call
//        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
//        redBagApiService.getBloodRequest(body).enqueue(onResponseListener)
//    }
}