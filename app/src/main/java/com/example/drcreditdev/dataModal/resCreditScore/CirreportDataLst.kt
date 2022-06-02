package com.example.example

import com.google.gson.annotations.SerializedName


data class CirreportDataLst (

  @SerializedName("inquiryResponseHeader" ) var inquiryResponseHeader : InquiryResponseHeader? = InquiryResponseHeader(),
  @SerializedName("inquiryRequestInfo"    ) var inquiryRequestInfo    : InquiryRequestInfo?    = InquiryRequestInfo(),
  @SerializedName("cirreportData"         ) var cirreportData         : CirreportData?         = CirreportData(),
  @SerializedName("score"                 ) var score                 : ArrayList<Score>       = arrayListOf()

)