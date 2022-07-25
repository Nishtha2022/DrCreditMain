package com.example.drcreditdev.dataModal

import com.google.gson.annotations.SerializedName

data class reqCreditScore(
    @SerializedName("fullName") val fullName : String?,
    @SerializedName("dob") val dob : String?,
    @SerializedName("pincode") val pincode : String?,
    @SerializedName("stateCode") val stateCode : String?,
    @SerializedName("panNumber") val panNumber: String?,
    @SerializedName("fatherName") val fatherName : String?,
    @SerializedName("address") val address : String?
)
