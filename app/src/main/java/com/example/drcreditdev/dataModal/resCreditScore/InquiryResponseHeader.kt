package com.example.example

import com.google.gson.annotations.SerializedName


data class InquiryResponseHeader (

  @SerializedName("date"          ) var date          : String?           = null,
  @SerializedName("time"          ) var time          : String?           = null,
  @SerializedName("customerName"  ) var customerName  : String?           = null,
  @SerializedName("reportOrderNO" ) var reportOrderNO : String?           = null,
  @SerializedName("productCode"   ) var productCode   : ArrayList<String> = arrayListOf(),
  @SerializedName("successCode"   ) var successCode   : String?           = null,
  @SerializedName("hitCode"       ) var hitCode       : String?           = null,
  @SerializedName("clientID"      ) var clientID      : String?           = null,
  @SerializedName("customerCode"  ) var customerCode  : String?           = null,
  @SerializedName("custRefField"  ) var custRefField  : String?           = null

)