package com.example.drcreditdev.dataModal

import com.google.gson.annotations.SerializedName


data class dataUser (

  @SerializedName("userID"                ) var userID                : String?  = null,
  @SerializedName("name"                  ) var name                  : String?  = null,
  @SerializedName("identifier"            ) var identifier            : String?  = null,
  @SerializedName("accountIdentifierType" ) var accountIdentifierType : String?  = null,
  @SerializedName("status"                ) var status                : String?  = null,
  @SerializedName("address"               ) var address               : String?  = null,
  @SerializedName("fatherName"            ) var fatherName            : String?  = null,
  @SerializedName("dob"                   ) var dob                   : String?  = null,
  @SerializedName("email"                 ) var email                 : String?  = null,
  @SerializedName("ledgerID"              ) var ledgerID              : String?  = null,
  @SerializedName("balance"               ) var balance               : Int?     = null,
  @SerializedName("limit"                 ) var limit                 : Int?     = null,
  @SerializedName("totalBillPaid"         ) var totalBillPaid         : Int?     = null,
  @SerializedName("currentOverdueAmount"  ) var currentOverdueAmount  : String?  = null,
  @SerializedName("role"                  ) var role                  : String?  = null,
  @SerializedName("gender"                ) var gender                : String?  = null,
  @SerializedName("maskedAadhaar"         ) var maskedAadhaar         : String?  = null,
  @SerializedName("pan"                   ) var pan                   : String?  = null,
  @SerializedName("kycStatus"             ) var kycStatus             : String?  = null,
  @SerializedName("isKycBlocked"          ) var isKycBlocked          : String?  = null,
  @SerializedName("isPinSet"              ) var isPinSet              : Boolean? = null,
  @SerializedName("vpa"                   ) var vpa                   : String?  = null

)