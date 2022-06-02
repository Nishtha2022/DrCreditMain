package com.example.example

import com.google.gson.annotations.SerializedName


data class EnquirySummary (

  @SerializedName("total"        ) var total        : String? = null,
  @SerializedName("purpose"      ) var purpose      : String? = null,
  @SerializedName("past30Days"   ) var past30Days   : String? = null,
  @SerializedName("past12Months" ) var past12Months : String? = null,
  @SerializedName("past24Months" ) var past24Months : String? = null,
  @SerializedName("recent"       ) var recent       : String? = null

)