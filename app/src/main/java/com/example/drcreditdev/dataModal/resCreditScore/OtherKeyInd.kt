package com.example.example

import com.google.gson.annotations.SerializedName


data class OtherKeyInd (

  @SerializedName("ageOfOldestTrade"             ) var ageOfOldestTrade             : String? = null,
  @SerializedName("numberOfOpenTrades"           ) var numberOfOpenTrades           : String? = null,
  @SerializedName("allLinesEVERWritten"          ) var allLinesEVERWritten          : String? = null,
  @SerializedName("allLinesEVERWrittenIn9Months" ) var allLinesEVERWrittenIn9Months : String? = null,
  @SerializedName("allLinesEVERWrittenIn6Months" ) var allLinesEVERWrittenIn6Months : String? = null

)