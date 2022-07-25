package com.example.drcreditdev.dataModal

import android.service.carrier.CarrierIdentifier
import com.google.gson.annotations.SerializedName

data class resVerify(

    @SerializedName("authToken") val authToken: String,
    @SerializedName("publicKey") val publicKey : String?,
    @SerializedName("validTill") val validTill : String?,
    @SerializedName("secretMessage") val secretMessage : String?)