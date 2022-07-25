package com.example.drcreditdev.creditCardUI.refreshPageUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drcreditdev.services.ApiClientRefresh
import kotlinx.coroutines.launch

class RefreshPageViewModel : ViewModel() {
    var intentInt: MutableLiveData<Int> = MutableLiveData(0)
    var score: MutableLiveData<String> = MutableLiveData()


    fun callRefreshApi(authToken: String) {
        val header = HashMap<String, String>()
        header["authToken"] = authToken
        header["Content-Type"] = "application/json"
        header["Cookie"] = "JSESSIONID=F7AC6F52A47B66466769E1EFE88BBA9B"

        var callRefreshApi = ApiClientRefresh().getApiService()
        viewModelScope.launch {
            val response = callRefreshApi.getRefreshScore(header)
            if (response?.body() != null) {
                if (response.code() == 200) {
                    if (response.body()!!.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData != null) {
                        var scoreV: String =
                            (response.body()!!.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)!!.toString()

                        score.value = scoreV
                        intentInt.value = 1
                    } else {
                        intentInt.value = 2
                    }

                } else {
                    intentInt.value = 2

                }
            }
            else {
                intentInt.value = 2

            }
        }
    }
}