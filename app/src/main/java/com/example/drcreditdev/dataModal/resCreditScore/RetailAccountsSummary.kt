package com.example.example

import com.google.gson.annotations.SerializedName


data class RetailAccountsSummary (

  @SerializedName("noOfZeroBalanceAccounts"        ) var noOfZeroBalanceAccounts        : String? = null,
  @SerializedName("totalCreditLimit"               ) var totalCreditLimit               : String? = null,
  @SerializedName("noOfActiveAccounts"             ) var noOfActiveAccounts             : String? = null,
  @SerializedName("totalHighCredit"                ) var totalHighCredit                : String? = null,
  @SerializedName("singleHighestSanctionAmount"    ) var singleHighestSanctionAmount    : String? = null,
  @SerializedName("totalSanctionAmount"            ) var totalSanctionAmount            : String? = null,
  @SerializedName("singleHighestBalance"           ) var singleHighestBalance           : String? = null,
  @SerializedName("noOfWriteOffs"                  ) var noOfWriteOffs                  : String? = null,
  @SerializedName("oldestAccount"                  ) var oldestAccount                  : String? = null,
  @SerializedName("noOfAccounts"                   ) var noOfAccounts                   : String? = null,
  @SerializedName("mostSevereStatusWithIn24Months" ) var mostSevereStatusWithIn24Months : String? = null,
  @SerializedName("singleHighestCredit"            ) var singleHighestCredit            : String? = null,
  @SerializedName("averageOpenBalance"             ) var averageOpenBalance             : String? = null,
  @SerializedName("noOfPastDueAccounts"            ) var noOfPastDueAccounts            : String? = null,
  @SerializedName("recentAccount"                  ) var recentAccount                  : String? = null,
  @SerializedName("totalBalanceAmount"             ) var totalBalanceAmount             : String? = null,
  @SerializedName("totalMonthlyPaymentAmount"      ) var totalMonthlyPaymentAmount      : String? = null,
  @SerializedName("totalPastDue"                   ) var totalPastDue                   : String? = null

)