package com.example.example

import com.google.gson.annotations.SerializedName


data class Payload (

  @SerializedName("inquiryResponseHeader" ) var inquiryResponseHeader : InquiryResponseHeader? = InquiryResponseHeader(),
  @SerializedName("inquiryRequestInfo"    ) var inquiryRequestInfo    : InquiryRequestInfo?    = InquiryRequestInfo(),
  @SerializedName("score"                 ) var score                 : ArrayList<Score>       = arrayListOf(),
  @SerializedName("error"                 ) var error                 : String?                = null,
  @SerializedName("ccrresponse"           ) var ccrresponse           : Ccrresponse?           = Ccrresponse()

)