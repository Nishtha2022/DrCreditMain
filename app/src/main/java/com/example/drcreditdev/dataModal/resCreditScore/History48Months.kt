package com.example.example

import com.google.gson.annotations.SerializedName


data class History48Months (

  @SerializedName("key"                       ) var key                       : String? = null,
  @SerializedName("paymentStatus"             ) var paymentStatus             : String? = null,
  @SerializedName("suitFiledStatus"           ) var suitFiledStatus           : String? = null,
  @SerializedName("assetClassificationStatus" ) var assetClassificationStatus : String? = null

)