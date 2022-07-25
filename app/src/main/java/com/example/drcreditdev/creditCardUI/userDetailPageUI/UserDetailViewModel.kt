package com.example.drcreditdev.creditCardUI.userDetailPageUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserDetailViewModel : ViewModel() {
    var pan: String? = null
    var fullName : String? = null
    var fatherName : String? = null
    var dob : String? = null
    var address : String? = null
    var state : String? = null
    var pinCode : String? = null
    var stateCode : String? = null
    var enableBtn : MutableLiveData<Boolean> = MutableLiveData(false)
    var setToast : MutableLiveData<Int> = MutableLiveData(0)





    fun OnClickProceedBtn(){

        stateCode  = when(state){
            "Andhra Pradesh" -> "AP"
            "Arunachal Pradesh"->"AR"
            "Assam"-> "AS"
            "Bihar"->"BR"
            "Chandigarh"->"CH"
            "Chhattisgarh"->"CT"
            "Dadra and Nagar Haveli"->"DN"
            "Daman and Diu"->"DD"
            "Delhi"->"DL"
            "Goa"->"GA"
            "Gujarat"->"GJ"
            "Haryana"-> "HR"
            "Himachal Pradesh"->"HP"
            "Jammu and Kashmir"->"JK"
            "Jharkhand"->"JH"
            "Karnataka"->"KA"
            "Kerala"->"KL"
            "Lakshadweep"->"LD"
            "Madhya Pradesh"->"MP"
            "Maharashtra"->"MH"
            "Manipur"->"MN"
            "Meghalaya"->"ML"
            "Mizoram"->"MZ"
            "Nagaland"->"NL"
            "Odisha"->"OR"
            "Puducherry"->"PY"
            "Punjab"->"PB"
            "Rajasthan"->"RJ"
            "Sikkim"->"SK"
            "Tamil Nadu"->"TN"
            "Telangana"->"TG"
            "Tripura"->"TR"
            "Uttar Pradesh"->"UP"
            "Uttarakhand"->"UT"
            "West Bengal"->"WB"
            else -> "none"

        }

        val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        val matcher: Matcher = pattern.matcher(pan)

        val pinCodeReg : Pattern = Pattern.compile("[1-9]{1}[0-9]{2}[0-9]{3}")
        val matcher_pan = pinCodeReg.matcher(pinCode)

        if(pan!!.isEmpty()
            ||fullName!!.isEmpty()
            ||fatherName!!.isEmpty()
            ||dob!!.isEmpty()
            ||address!!.isEmpty()
            ||state!!.isEmpty()
            ||pinCode!!.isEmpty())
        {
            setToast.value = 1
        }
        else if(!(matcher.matches()))
        {
            setToast.value = 2
        }
        else if(!(matcher_pan.matches()))
        {
            setToast.value = 3
        }
        else
        {
            setToast.value = 4
        }
    }



}