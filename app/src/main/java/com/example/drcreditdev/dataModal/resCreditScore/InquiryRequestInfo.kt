package com.example.example

import com.google.gson.annotations.SerializedName


data class InquiryRequestInfo (

  @SerializedName("dob"              ) var dob              : String?                     = null,
  @SerializedName("inquiryPurpose"   ) var inquiryPurpose   : String?                     = null,
  @SerializedName("firstName"        ) var firstName        : String?                     = null,
  @SerializedName("inquiryAddresses" ) var inquiryAddresses : ArrayList<InquiryAddresses> = arrayListOf(),
  @SerializedName("inquiryPhones"    ) var inquiryPhones    : ArrayList<InquiryPhones>    = arrayListOf(),
  @SerializedName("iddetails"        ) var iddetails        : ArrayList<Iddetails>        = arrayListOf(),
  @SerializedName("mfidetails"       ) var mfidetails       : Mfidetails?                 = Mfidetails()

)