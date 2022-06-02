package com.example.drcreditdev.dataModal

import com.google.gson.annotations.SerializedName

data class reqVerify(
    @SerializedName("identifier") val identifier: String?,
    @SerializedName("otp") val otp:Int?,
    @SerializedName("accountIdentifierType") val accountIdentifierType: String?
)
