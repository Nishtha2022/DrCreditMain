package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("payload" ) var payload : Payload? = Payload()

)