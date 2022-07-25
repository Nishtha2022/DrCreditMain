package com.example.drcreditdev.repository

import com.example.drcreditdev.dataModal.*
import com.example.example.ExampleJson2KtKotlin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {
    @POST("requestOTP")
  suspend fun createPost(@Body dataModal: reqGenrateOtp?): Response<ResponseBody>

   @POST("verifyOTP")
   suspend fun login(@Body dataModal: reqVerify?): Response<resVerify?>?


    @POST("UnverifiedUser")
  suspend fun fetchCreditScore(@HeaderMap headers: HashMap<String,String>,@Body dataModal:reqCreditScore?):Response<ExampleJson2KtKotlin?>?

    @GET("refreshCreditScore")
  suspend fun getRefreshScore(@HeaderMap headers: HashMap<String, String>):Response<ExampleJson2KtKotlin?>?

    @GET("user/")
  suspend fun fetchUser(@HeaderMap headers: HashMap<String, String>):Response<dataUser>

}