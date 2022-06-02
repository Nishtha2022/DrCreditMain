package com.example.example

import com.google.gson.annotations.SerializedName


data class PersonalInfo (

  @SerializedName("gender"           )          var gender           : String?           = null,                
  @SerializedName("dateOfBirth"      )          var dateOfBirth      : String?           = null,                
  @SerializedName("placeOfBirthInfo" )          var placeOfBirthInfo : PlaceOfBirthInfo? = PlaceOfBirthInfo("none"),
  @SerializedName("totalIncome"      )          var totalIncome      : String?           = null,                
  @SerializedName("name"             )          var name             : Name?             = Name(),              
  @SerializedName("                  AliasName" )   var                AliasName         : String?             = null

)