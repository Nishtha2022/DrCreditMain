package com.example.example

import com.google.gson.annotations.SerializedName


data class Enquiries (

  @SerializedName("date"           ) var date           : String? = null,
  @SerializedName("time"           ) var time           : String? = null,
  @SerializedName("type"           ) var type           : String? = null,
  @SerializedName("institution"    ) var institution    : String? = null,
  @SerializedName("requestPurpose" ) var requestPurpose : String? = null

)