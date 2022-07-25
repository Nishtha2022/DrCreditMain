package com.example.drcreditdev.dataModal

import com.google.gson.annotations.SerializedName

data class reqGenrateOtp (
    @SerializedName("identifier") val identifier: String?,
    @SerializedName("publicKey") val publicKey:String?,
    @SerializedName("accountIdentifierType") val accountIdentifierType: String?

)
