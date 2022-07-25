package com.example.drcreditdev.login.otpVerificationUI

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCardUI.refreshPageUI.RefreshPage
import com.example.drcreditdev.creditCardUI.userDetailPageUI.User_details
import com.example.drcreditdev.databinding.ActivityOtpVerificationBinding
import com.example.drcreditdev.login.userNumberUI.UserNumber

class Otp_verification : AppCompatActivity() {

    lateinit var str : String
    lateinit var binding : ActivityOtpVerificationBinding //= ActivityOtpVerificationBinding.inflate(layoutInflater)
    var viewModel : OtpVerificationViewModel = OtpVerificationViewModel() //= ViewModelProvider(this).get(OtpVerificationViewModel::class.java)
    lateinit var sharedPreferences: SharedPreferences


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var viewModel  = ViewModelProvider(this).get(OtpVerificationViewModel::class.java)
        binding.otpView = viewModel
        binding.editText1!!.requestFocus()
        binding.btnVerify.isEnabled = false
        sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)

  //      var textView : TextView = findViewById(R.id.textView2)



        if(intent.getStringExtra("phone")!=null) {
            str = intent.getStringExtra("phone") ?: "12345"
            binding.textView2.text = str
            viewModel.phone = str
        }
        setupOtpInput()
        startTimeCounter(this)
        binding.imgBack.setOnClickListener(View.OnClickListener {
            var intent = Intent(applicationContext, UserNumber::class.java)
            startActivity(intent)
            finish()
        })

        binding.editText1!!.setOnKeyListener(GenricKeyEvent(binding.editText1!!, null))
        binding.editText2!!.setOnKeyListener(GenricKeyEvent(binding.editText2!!, binding.editText1))
        binding.editText3!!.setOnKeyListener(GenricKeyEvent(binding.editText3!!, binding.editText2))
        binding.editText4!!.setOnKeyListener(GenricKeyEvent(binding.editText4!!,binding.editText3))
        binding.editText5!!.setOnKeyListener(GenricKeyEvent(binding.editText5!!,binding.editText4))
        binding.editText6!!.setOnKeyListener(GenricKeyEvent(binding.editText6!!,binding.editText5))



       /* OtpVerificationViewModel().setVerifyButton.observe(this,{
            if(OtpVerificationViewModel().setVerifyButton.value == true)
            {
                binding.anime.visibility = View.VISIBLE
                binding.anime.playAnimation()
                binding.btnVerify.visibility = View.GONE
                verifyButton()
            }
            else
            {
                binding.btnVerify.visibility = View.INVISIBLE
                binding.anime.visibility = View.GONE
            }
        })*/



    }

    private fun changePage() {

        viewModel.intentInt.observe(this,{
            if(it == 0)
            {

                  var intent = Intent(applicationContext, User_details::class.java)
                  startActivity(intent)
                  finish()
            }
            else if(it == 1)
            {
                var intent = Intent(applicationContext, RefreshPage::class.java)
                startActivity(intent)
                finish()

            }
            else
            {
                Log.d("msg",viewModel.intentInt.value.toString())
            }
        })
    }

    private fun verifyButton() {
       viewModel.etText1 = binding.editText1.text.toString()
       viewModel.etText2 = binding.editText2.text.toString()
       viewModel.etText3 = binding.editText3.text.toString()
       viewModel.etText4 = binding.editText4.text.toString()
       viewModel.etText5 = binding.editText5.text.toString()
       viewModel.etText6 = binding.editText6.text.toString()

       viewModel.enableVerifyButton()

       if(viewModel.setVerifyButton.value == true)
       {
           binding.anime.visibility = View.VISIBLE
           binding.anime.playAnimation()
           binding.btnVerify.visibility = View.GONE
           var code: String = binding.editText1!!.text.toString() +
                   binding.editText2!!.text.toString() +
                   binding.editText3!!.text.toString() +
                   binding.editText4!!.text.toString() +
                   binding.editText5!!.text.toString() +
                   binding.editText6!!.text.toString()
           verifyOTP(code, str)

       }
       else
       {
           binding.anime.visibility = View.GONE
           binding.btnVerify.visibility = View.VISIBLE

           Toast.makeText(this,"OTP is Invalid" ,Toast.LENGTH_SHORT).show()

           binding.editText1!!.text.clear()
           binding.editText2!!.text.clear()
           binding.editText3!!.text.clear()
           binding.editText4!!.text.clear()
           binding.editText5!!.text.clear()
           binding.editText6!!.text.clear()

           setupOtpInput()

       }



    }

    private fun verifyOTP(code: String, phone: String) {

        viewModel.requestApi(code,phone)
        (viewModel.resetOtp.observe(this, {
            if (it == true) {

                Toast.makeText(this.applicationContext, "Invalid OTP", Toast.LENGTH_SHORT).show()
                binding.btnVerify.visibility = View.VISIBLE
                binding.anime.visibility = View.GONE
                binding.editText6!!.setBackgroundResource(R.drawable.user_detail_box)
                binding.editText1!!.text.clear()
                binding.editText2!!.text.clear()
                binding.editText3!!.text.clear()
                binding.editText4!!.text.clear()
                binding.editText5!!.text.clear()
                binding.editText6!!.text.clear()
                binding.editText1!!.requestFocus()
                binding.editText1!!.setBackgroundResource(R.drawable.number_bg_on_click)


            }
            else{
                viewModel.authToken.observe(this,{
                    if(it!=null)
                    {
                        sharedPreferences = getSharedPreferences("drFile",MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("authToken",it)
                        editor.apply()
                    }

                })
                changePage()
            }


        }))
    }






    fun setupOtpInput() {          //text watcher for all the boxes when number is entered


        val textWatcher1 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    binding.editText2!!.requestFocus()
                    binding.editText2!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    binding.editText1!!.setBackgroundResource(R.drawable.user_detail_box)

                }
            }
        }
        val textWatcher2 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    binding.editText3!!.requestFocus()
                    binding.editText3!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    binding.editText2!!.setBackgroundResource(R.drawable.user_detail_box)
                }
            }
        }
        val textWatcher3 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    binding.editText4!!.requestFocus()
                    binding.editText4!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    binding.editText3!!.setBackgroundResource(R.drawable.user_detail_box)
                }
            }
        }
        val textWatcher4 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    binding.editText5!!.requestFocus()
                    binding.editText5!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    binding.editText4!!.setBackgroundResource(R.drawable.user_detail_box)
                }
            }
        }
        val textWatcher5 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    binding.editText6!!.requestFocus()
                    binding.editText6!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    binding.editText5!!.setBackgroundResource(R.drawable.user_detail_box)

                }
            }
        }
        val textWatcher6 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    verifyButton()

                }
            }
        }

        binding.editText1!!.addTextChangedListener(textWatcher1)
        binding.editText2!!.addTextChangedListener(textWatcher2)
        binding.editText3!!.addTextChangedListener(textWatcher3)
        binding.editText4!!.addTextChangedListener(textWatcher4)
        binding.editText5!!.addTextChangedListener(textWatcher5)
        binding.editText6!!.addTextChangedListener(textWatcher6)
    }

    fun startTimeCounter(view: Otp_verification) {
        var counter = 30
        val countTime: TextView = findViewById(R.id.timer)
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = "00:"+counter.toString()+" s"
                counter--
            }
            override fun onFinish() {
                countTime.text = "Resend code"
                countTime.setTextColor(Color.parseColor("#229381"))

            }
        }.start()
    }


    class GenricKeyEvent internal constructor(private val currentView: EditText, private val previousView: EditText?) : View.OnKeyListener{
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.editText1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                currentView.setBackgroundResource(R.drawable.user_detail_box)
                previousView.setBackgroundResource(R.drawable.number_bg_on_click)
                return true
            }
            return false
        }


    }}


