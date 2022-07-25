package com.example.drcreditdev.creditCardUI.proceedingPageUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drcreditdev.dataModal.reqCreditScore
import com.example.drcreditdev.services.ApiClientCredit
import com.example.example.ExampleJson2KtKotlin
import kotlinx.coroutines.launch


class ProceedingPageViewModel: ViewModel()
{

    var authToken : String? = "token"
    var pan:String? = null
    var fullName:String? = null
    var fatherName:String? = null
    var dob:String? = null
    var state:String? = null
    var address :String? = null
    var pinCode:String? = null
    var intentInt : MutableLiveData<Int> = MutableLiveData(0)
    var score : MutableLiveData<String> = MutableLiveData()





    fun callCreditApi(authToken : String){
        val header = HashMap<String, String>()
        header["authToken"] = authToken!!
        header["Content-Type"] = "application/json"
        header["Cookie"] = "JSESSIONID=F7AC6F52A47B66466769E1EFE88BBA9B"
        var model = reqCreditScore(fullName,dob,pinCode,state,pan,fatherName,address)
        var responseFApi :MutableLiveData<ExampleJson2KtKotlin> = MutableLiveData()
        val creditApiReq = ApiClientCredit().getApiService()
        viewModelScope.launch {
            val response = creditApiReq.fetchCreditScore(header,model)
            if(response!!.body()!=null)
            {
                if(response.code()==200)
                {

                  responseFApi.postValue(response.body())
                    if(response.body()!!.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!=null)
                    {
                         var scoreV  =
                            (response.body()!!.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)!!.toString()
                                .trim()

                        if(scoreV != "-1" && scoreV!= null )
                        {
                            score.value = scoreV
                            intentInt.value = 1
                        }
                    }
                }
                else
                {
                   intentInt.value = 2
                }
            }
            else{

                intentInt.value = 2

            }
        }
    }


}