package com.example.drcreditdev.login.otpVerificationUI

import androidx.lifecycle.*
import com.example.drcreditdev.dataModal.dataUser
import com.example.drcreditdev.dataModal.reqVerify
import com.example.drcreditdev.dataModal.resVerify
import com.example.drcreditdev.services.ApiClientReqOTP
import com.example.drcreditdev.services.ApiClientUser
import kotlinx.coroutines.launch

class OtpVerificationViewModel : ViewModel(){

     var etText1 :String? = null
     var etText2  :String? = null
     var etText3  :String? = null
     var etText4  :String? = null
     var etText5  :String? = null
    var etText6  :String? = null
    var isVerified : MutableLiveData<Boolean> = MutableLiveData(false)
    var setVerifyButton : MutableLiveData<Boolean> = MutableLiveData(false)
    var resetOtp : MutableLiveData<Boolean> = MutableLiveData(false)
    var intentInt : MutableLiveData<Int> = MutableLiveData()
    lateinit var phone : String
    var authToken: MutableLiveData<String> = MutableLiveData()



    fun enableVerifyButton()
    {
        if(!etText1.isNullOrEmpty()
            &&!etText2.isNullOrEmpty()
            &&!etText3.isNullOrEmpty()
            &&!etText4.isNullOrEmpty()
            &&!etText5.isNullOrEmpty()
            &&!etText6.isNullOrEmpty()
        )
        {
           setVerifyButton.value = true
        }
        else
        {
            setVerifyButton.value = false
        }

    }

  fun requestApi(code:String,phone: String)
  {
      var number = "+91"+phone
      var model : reqVerify = reqVerify(number,code.toInt(),"phone")
      var response : MutableLiveData<resVerify> = MutableLiveData()

      val reqOtp = ApiClientReqOTP().getApiService()
      viewModelScope.launch {
          val result= reqOtp.login(model)
          if(result!=null)
          {
              if(result.isSuccessful)
              {
                  resetOtp.value=false
                  response.value = result.body()
                  authToken.value = result.body()!!.authToken
                  checkUser(result.body()!!.authToken)

              }
              else
              {
                resetOtp.value=true
              }



          }
          else
          {
              resetOtp.value = true
          }

      }

  }

    private fun checkUser(authToken: String) {
        var response: MutableLiveData<dataUser> = MutableLiveData()
        val header = HashMap<String, String>()
        header["authToken"] = authToken!!
        header["Cookie"] = "JSESSIONID=17D464BCCAB458C440F98723E9F1F208"
        var reqUserApi = ApiClientUser().getApiService()
        viewModelScope.launch {
            val response = reqUserApi.fetchUser(header)
            if (response.body() != null) {
                if (response.isSuccessful || response.code() == 200) {
                    if (response.body()!!.pan == null || response.body()!!.fatherName == null || response.body()!!.dob == null) {
                       intentInt.postValue(0)
                        isVerified.value = false
                    }
                    else
                    {
                        intentInt.postValue(1)
                        isVerified.value = true
                    }
                }
                else
                {
                    intentInt.postValue(0)
                    isVerified.value= false
                }
            }
            else
            {
                intentInt.postValue(0)
                isVerified.value = false
            }


        }


    }
}