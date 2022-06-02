package com.example.example

import com.google.gson.annotations.SerializedName


data class Iddetails (

  @SerializedName("seq"     ) var seq     : String? = null,
  @SerializedName("source"  ) var source  : String? = null,
  @SerializedName("idtype"  ) var idtype  : String? = null,
  @SerializedName("idvalue" ) var idvalue : String? = null

)