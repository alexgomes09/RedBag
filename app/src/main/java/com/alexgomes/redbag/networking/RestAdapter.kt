package com.alexgomes.redbag.networking

import com.alexgomes.redbag.BuildConfig
import com.alexgomes.redbag.RedBagApplication
import com.alexgomes.redbag.Util
import com.alexgomes.redbag.networking.reqest.CreateProfileModel
import com.alexgomes.redbag.networking.reqest.RequestBloodModel
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

    fun createUserProfile(profileModel: CreateProfileModel,onResponseListener: Callback<Void>) {
        //we check internet connection before making every network call
        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
        redBagApiService.createUserProfile(profileModel).enqueue(onResponseListener)
    }

    fun requestBlood(requestBloodModel: RequestBloodModel,onResponseListener: Callback<Void>) {
        //we check internet connection before making every network call
        if (!Util.checkForInternet(RedBagApplication.applicationContext())) return
        redBagApiService.requestBlood(requestBloodModel).enqueue(onResponseListener)
    }
}