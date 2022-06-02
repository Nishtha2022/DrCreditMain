package com.example.example

import com.google.gson.annotations.SerializedName


data class InquiryAddresses (

  @SerializedName("seq"          ) var seq          : String?           = null,
  @SerializedName("state"        ) var state        : String?           = null,
  @SerializedName("addressType"  ) var addressType  : ArrayList<String> = arrayListOf(),
  @SerializedName("addressLine1" ) var addressLine1 : String?           = null,
  @SerializedName("postal"       ) var postal       : String?           = null

)