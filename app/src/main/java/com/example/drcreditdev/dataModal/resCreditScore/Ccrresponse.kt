package com.example.example

import com.google.gson.annotations.SerializedName


data class Ccrresponse (

  @SerializedName("status"           ) var status           : String?                     = null,
  @SerializedName("cirreportDataLst" ) var cirreportDataLst : ArrayList<CirreportDataLst> = arrayListOf()

)