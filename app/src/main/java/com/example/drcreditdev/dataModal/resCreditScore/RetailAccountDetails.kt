package com.example.example

import com.google.gson.annotations.SerializedName


data class RetailAccountDetails (

  @SerializedName("seq"                 ) var seq                 : String?                    = null,
  @SerializedName("source"              ) var source              : String?                    = null,
  @SerializedName("reason"              ) var reason              : String?                    = null,
  @SerializedName("accountStatus"       ) var accountStatus       : String?                    = null,
  @SerializedName("balance"             ) var balance             : String?                    = null,
  @SerializedName("open"                ) var open                : String?                    = null,
  @SerializedName("collateralType"      ) var collateralType      : String?                    = null,
  @SerializedName("accountNumber"       ) var accountNumber       : String?                    = null,
  @SerializedName("institution"         ) var institution         : String?                    = null,
  @SerializedName("accountType"         ) var accountType         : String?                    = null,
  @SerializedName("ownershipType"       ) var ownershipType       : String?                    = null,
  @SerializedName("pastDueAmount"       ) var pastDueAmount       : String?                    = null,
  @SerializedName("writeOffAmount"      ) var writeOffAmount      : String?                    = null,
  @SerializedName("highCredit"          ) var highCredit          : String?                    = null,
  @SerializedName("dateReported"        ) var dateReported        : String?                    = null,
  @SerializedName("dateOpened"          ) var dateOpened          : String?                    = null,
  @SerializedName("dateClosed"          ) var dateClosed          : String?                    = null,
  @SerializedName("creditLimit"         ) var creditLimit         : String?                    = null,
  @SerializedName("sanctionAmount"      ) var sanctionAmount      : String?                    = null,
  @SerializedName("installmentAmount"   ) var installmentAmount   : String?                    = null,
  @SerializedName("lastPaymentDate"     ) var lastPaymentDate     : String?                    = null,
  @SerializedName("assetClassification" ) var assetClassification : String?                    = null,
  @SerializedName("interestRate"        ) var interestRate        : String?                    = null,
  @SerializedName("repaymentTenure"     ) var repaymentTenure     : String?                    = null,
  @SerializedName("termFrequency"       ) var termFrequency       : String?                    = null,
  @SerializedName("collateralValue"     ) var collateralValue     : String?                    = null,
  @SerializedName("history48Months"     ) var history48Months     : ArrayList<History48Months> = arrayListOf(),
  @SerializedName("lastPayment"         ) var lastPayment         : String?                    = null

)