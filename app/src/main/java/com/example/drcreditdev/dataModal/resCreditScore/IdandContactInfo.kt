package com.example.example

import com.example.example.PersonalInfo
import com.google.gson.annotations.SerializedName


data class IdandContactInfo (

  @SerializedName("personalInfo" ) var personalInfo : PersonalInfo? = PersonalInfo()

)