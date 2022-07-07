package com.example.drcreditdev.login.userNumberUI

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.drcreditdev.R
import com.example.drcreditdev.databinding.ActivityUserNumberBinding
import com.example.drcreditdev.login.otpVerificationUI.Otp_verification
import com.example.drcreditdev.services.ApiClientReqOTP
import kotlinx.android.synthetic.main.activity_user_number.*

class UserNumber : AppCompatActivity() {
    private lateinit var apiClient: ApiClientReqOTP
    lateinit var  binding : ActivityUserNumberBinding
    lateinit var viewModel : ViewForNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding  = ActivityUserNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewForNumber::class.java)
        binding.userNumber = viewModel
        binding.lifecycleOwner = this
        binding.etGetNumber.requestFocus()

        binding.etGetNumber.addTextChangedListener(textWatcher)
        var phone = binding.etGetNumber.text.toString()
        onCheckboxClicked(binding.checkBox)

        binding.btnBack.setOnClickListener(View.OnClickListener {

            finish()

        })
        binding.btnVerify.setOnClickListener(View.OnClickListener{
            var phoneNo = binding.etGetNumber!!.getText().toString()

            if(viewModel.isValid(phoneNo))
            {
                binding.anime.visibility= View.VISIBLE
                binding.anime.playAnimation()
                binding.btnVerify.visibility = View.INVISIBLE
                val phone = binding.etGetNumber!!.getText().toString()
                viewModel.callRepo(phone)
                OtpPage()


            }
            else
            {
                Toast.makeText(
                    this,
                    "Please enter a valid phone number.",
                    Toast.LENGTH_SHORT
                ).show()
                OtpPage()


            }
        })

        // Proceed Button Eanable
        viewModel.proceed.observe(this){
            if(it == true )
            {
                this@UserNumber.binding.btnVerify.isEnabled= true
                this@UserNumber.binding.btnVerify.backgroundTintList= ColorStateList.valueOf(
                    ResourcesCompat.getColor(resources,
                        R.color.button, theme))
                this@UserNumber.binding.btnVerify.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else
            {
                this@UserNumber.binding.btnVerify.isEnabled= false
                this@UserNumber.binding.btnVerify.backgroundTintList= ColorStateList.valueOf(
                    ResourcesCompat.getColor(resources,
                        R.color.disable, theme))
                this@UserNumber.binding.btnVerify.setTextColor(Color.parseColor("#8897A2"))
            }
        }


    }

    private fun OtpPage() {
        var number = binding.etGetNumber.text.toString()
        viewModel.repoResponse.observe(this,{
            if(it==1) {
                Toast.makeText(this, " OTP has been sent", Toast.LENGTH_SHORT).show()
                var intent = Intent(applicationContext, Otp_verification::class.java)
                intent.putExtra("phone", number)
                startActivity(intent)
                finish()
            }
            else if(it==0)
            {

                Toast.makeText(this@UserNumber,"Unable to send otp", Toast.LENGTH_SHORT).show()
                binding.btnVerify.visibility= View.VISIBLE
                binding.anime.visibility = View.INVISIBLE
            }

        })


    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            this@UserNumber.binding.linearLayout.setBackgroundResource(R.drawable.number_bg_on_click)
            //viewModel.proceed.postValue(true)
            //onCheckboxClicked(binding.checkBox)


        }

        override fun afterTextChanged(s: Editable?) {

        }
    }



    fun onCheckboxClicked(view: View){
      var phone = binding.etGetNumber.text.toString()

        if(checkBox.isChecked && viewModel.isValid(phone) )
        {
         viewModel.proceed.value = true
        }
        else
        {
            viewModel.proceed.value = false

        }

    }


}