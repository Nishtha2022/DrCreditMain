package com.example.example

import com.google.gson.annotations.SerializedName


data class CirreportData (

  @SerializedName("scoreDetails"          ) var scoreDetails          : ArrayList<ScoreDetails>         = arrayListOf(),
  @SerializedName("enquirySummary"        ) var enquirySummary        : EnquirySummary?                 = EnquirySummary(),
  @SerializedName("otherKeyInd"           ) var otherKeyInd           : OtherKeyInd?                    = OtherKeyInd(),
  @SerializedName("recentActivities"      ) var recentActivities      : RecentActivities?               = RecentActivities(),
  @SerializedName("idandContactInfo"      ) var idandContactInfo      : IdandContactInfo?               = IdandContactInfo(),
  @SerializedName("retailAccountDetails"  ) var retailAccountDetails  : ArrayList<RetailAccountDetails> = arrayListOf(),
  @SerializedName("retailAccountsSummary" ) var retailAccountsSummary : RetailAccountsSummary?          = RetailAccountsSummary(),
  @SerializedName("enquiries"             ) var enquiries             : ArrayList<Enquiries>            = arrayListOf()

)