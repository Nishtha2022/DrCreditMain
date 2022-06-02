package com.example.example

import com.google.gson.annotations.SerializedName


data class Score (

  @SerializedName("version" ) var version : String? = null,
  @SerializedName("type"    ) var type    : String? = null

)