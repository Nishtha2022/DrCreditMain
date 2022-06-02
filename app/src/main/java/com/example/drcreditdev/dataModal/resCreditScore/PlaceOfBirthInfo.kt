package com.example.example

import com.google.gson.annotations.SerializedName


data class PlaceOfBirthInfo (
    @SerializedName("place") var place : String? = "none"

)