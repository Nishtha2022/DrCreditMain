package com.example.drcreditdev.login.userNumberUI

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drcreditdev.dataModal.reqGenrateOtp
import com.example.drcreditdev.services.ApiClientReqOTP

import kotlinx.coroutines.launch

class ViewForNumber: ViewModel() {

    val repoResponse: MutableLiveData<Int> = MutableLiveData(2)
    var proceed : MutableLiveData<Boolean> = MutableLiveData(false)





     fun callRepo(phone : String) {
         var numberToSend = "+91"+phone
         var modal: reqGenrateOtp =reqGenrateOtp(numberToSend, "fdsfsdfdssf", "phone")
         val createPostApi = ApiClientReqOTP().getApiService()

        viewModelScope.launch {
            val result = createPostApi.createPost(modal)
            if (result != null)
            // Checking the results
                if (result.code() == 200) {
                    repoResponse.value = 1
                    Log.d("msg: ", "Success")
                }
            else
            {
                repoResponse.value = 0
            }
        }
    }



     fun isValid(number1: String?) : Boolean{
       if(number1!!.length!! <10 || number1.isNullOrEmpty())
       {
          return false

       }
        else
        {
            return  true
        }
    }

    }

