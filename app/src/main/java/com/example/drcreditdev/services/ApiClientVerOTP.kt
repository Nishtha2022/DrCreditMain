package com.example.drcreditdev.services

import com.example.drcreditdev.repository.RetrofitApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientVerOTP {
    private  val base_url = "https://stage.terrafin.tech:8090/user/"
    private lateinit var apiService: RetrofitApi

    fun getApiService(): RetrofitApi {


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()

            apiService = retrofit.create(RetrofitApi::class.java)
        }

        return apiService
    }


}