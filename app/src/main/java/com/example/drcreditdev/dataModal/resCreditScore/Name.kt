package com.example.example

import com.google.gson.annotations.SerializedName


data class Name (

  @SerializedName("fullName"  ) var fullName  : String? = null,
  @SerializedName("firstName" ) var firstName : String? = null,
  @SerializedName("lastName"  ) var lastName  : String? = null

)