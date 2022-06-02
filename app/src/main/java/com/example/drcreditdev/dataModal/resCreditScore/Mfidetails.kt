package com.example.example

import com.google.gson.annotations.SerializedName


data class Mfidetails (

  @SerializedName("familyDetails" ) var familyDetails : ArrayList<FamilyDetails> = arrayListOf()

)