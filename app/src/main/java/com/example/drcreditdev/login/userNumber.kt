package com.example.drcreditdev.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.drcreditdev.R
import com.example.drcreditdev.dataModal.reqGenrateOtp
import com.example.drcreditdev.services.RetrofitApi
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.concurrent.TimeUnit
import android.widget.Toast.makeText as toastMakeText

class userNumber : AppCompatActivity() {
    lateinit var editText : EditText
    lateinit var getOtpButton : Button
    lateinit var anime : LottieAnimationView
    lateinit var checkBox : CheckBox
    lateinit var editTextBg : LinearLayout
    lateinit var btnBack : ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_user_number)


        btnBack = findViewById(R.id.btnBack)
        checkBox= findViewById(R.id.checkBox)
        editTextBg = findViewById(R.id.linearLayout)
        anime = findViewById(R.id.anime)
        editText = findViewById(R.id.etGetNumber)
        getOtpButton = findViewById(R.id.btnVerify)
        editText.requestFocus()
        editText.addTextChangedListener(textWatcher)
        onCheckboxClicked(checkBox)
        btnBack.setOnClickListener(View.OnClickListener {

            finish()

        })
        getOtpButton.setOnClickListener(View.OnClickListener{
            var phoneNo = editText!!.getText().toString()
            if (TextUtils.isEmpty(editText!!.getText().toString())) {
                toastMakeText(
                    this,
                    "Please enter a phone number.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if(phoneNo.length< 10)
            {
                toastMakeText(
                    this,
                    "Please enter a valid phone number.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            else
            {
                anime.visibility= View.VISIBLE
                anime.playAnimation()
                getOtpButton.visibility = View.INVISIBLE
                val phone = editText!!.getText().toString()
                sendVerificationCode(phone)


            }
        })




    }
    private fun sendVerificationCode(number: String) {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            //https://devapi.terrafin.tech:8090/
            .baseUrl("https://stage.terrafin.tech:8090/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        var phone = "+91"+number
        val retrofitAPI = retrofit.create(RetrofitApi::class.java)
        val modal = reqGenrateOtp(phone, "fdsfsdfdssf", "phone")
        val call: Call<ResponseBody?>? = retrofitAPI.createPost(modal)
        call!!.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(this@userNumber, "OTP has been sent", Toast.LENGTH_SHORT).show();
                if(response.code()==200)
                {
                    var intent = Intent(applicationContext,Otp_verification::class.java)
                    intent.putExtra("phone",number)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@userNumber, """${response.code()}+:+${response.message()}""", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@userNumber,"Error found is : " + t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            editTextBg.setBackgroundResource(R.drawable.number_bg_on_click)
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }
    fun onCheckboxClicked(view: View){
        if(checkBox.isChecked)
        {
            getOtpButton.isEnabled= true
            getOtpButton.backgroundTintList= ColorStateList.valueOf(
                ResourcesCompat.getColor(resources,
                    R.color.button, theme))
            getOtpButton.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else{

            getOtpButton.isEnabled= false
            getOtpButton.backgroundTintList= ColorStateList.valueOf(
                ResourcesCompat.getColor(resources,
                    R.color.disable, theme))
            getOtpButton.setTextColor(Color.parseColor("#8897A2"))

        }

    }



}