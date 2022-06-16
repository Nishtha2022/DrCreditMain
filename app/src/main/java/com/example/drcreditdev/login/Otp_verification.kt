package com.example.drcreditdev.login

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCard.RefreshPage
import com.example.drcreditdev.creditCard.error_page
import com.example.drcreditdev.creditCard.user_details
import com.example.drcreditdev.dataModal.*
import com.example.drcreditdev.services.RetrofitApi
import com.example.example.dataUser
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Otp_verification : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    var editText1: EditText? = null
    var editText2: EditText? = null
    var editText3: EditText? = null
    var editText4: EditText?= null
    var editText5: EditText? = null
    var editText6: EditText? = null
    var tvEnterOtp : TextView? = null
    lateinit var imgBack : ImageView
    lateinit var anime : LottieAnimationView
    lateinit var verifyBtn : Button
    lateinit var str : String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        verifyBtn  = findViewById(R.id.btnVerify)
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        editText5 = findViewById(R.id.editText5)
        editText6 = findViewById(R.id.editText6)
        imgBack = findViewById(R.id.imgBack)
        anime = findViewById(R.id.anime)
        tvEnterOtp = findViewById(R.id.tvEnterOtp)
        editText1!!.requestFocus()
        verifyBtn.isEnabled = false


        var textView : TextView = findViewById(R.id.textView2)

        if(intent.getStringExtra("phone")!=null) {
            str = intent.getStringExtra("phone") ?: "12345"
            textView.text = str
        }
        else if(intent.getStringExtra("message")!=null||intent.getStringExtra("phone2")!=null)
        {
            tvEnterOtp!!.text = intent.getStringExtra("message").toString()
            tvEnterOtp!!.setTextColor(Color.parseColor("#ff0000"))
            str = intent.getStringExtra("phone2").toString()
            textView.text = str

        }
        setupOtpInput()
        startTimeCounter(this)
        imgBack.setOnClickListener(View.OnClickListener {
            var intent = Intent(applicationContext,userNumber::class.java)
            startActivity(intent)
            finish()
        })

        editText1!!.setOnKeyListener(GenricKeyEvent(editText1!!, null))
        editText2!!.setOnKeyListener(GenricKeyEvent(editText2!!, editText1))
        editText3!!.setOnKeyListener(GenricKeyEvent(editText3!!, editText2))
        editText4!!.setOnKeyListener(GenricKeyEvent(editText4!!,editText3))
        editText5!!.setOnKeyListener(GenricKeyEvent(editText5!!,editText4))
        editText6!!.setOnKeyListener(GenricKeyEvent(editText6!!,editText5))



    }
    private fun verifyButton() {

        if(editText1!!.text.toString().trim().isEmpty()
            || editText2!!.text.toString().trim().isEmpty()
            || editText3!!.text.toString().trim().isEmpty()
            || editText4!!.text.toString().trim().isEmpty()
            || editText5!!.text.toString().trim().isEmpty()
            || editText6!!.text.toString().trim().isEmpty())
        {
            Toast.makeText(this,"please enter valid otp",Toast.LENGTH_SHORT).show()

        }
        else {
            anime.visibility = View.VISIBLE
            anime.playAnimation()
            verifyBtn.visibility = View.GONE

            var code: String = editText1!!.text.toString() +
                    editText2!!.text.toString() +
                    editText3!!.text.toString() +
                    editText4!!.text.toString() +
                    editText5!!.text.toString() +
                    editText6!!.text.toString()
            verifyOTP(code, str)
        }
    }

    private fun verifyOTP(code: String, phone: String) {

        apiClient = ApiClient()
        sessionManager = SessionManager(this@Otp_verification)
        var number = "+91"+phone

        var varCode = code.toInt()
        if(varCode == 890123) {

            apiClient.getApiService().login(reqVerify(number, 890123, "phone"))!!
                .enqueue(object : Callback<resVerify?> {
                    override fun onResponse(
                        call: Call<resVerify?>,
                        response: Response<resVerify?>
                    ) {
                        Toast.makeText(
                            this@Otp_verification,
                            "OTP is verrified",
                            Toast.LENGTH_SHORT
                        ).show()
                        val loginResponse = response.body()
                        if (response.code() == 200) {
                            if (loginResponse != null) {
                                sharedPreferences =  getSharedPreferences("drFile", MODE_PRIVATE)
                                var editor = sharedPreferences.edit()
                                editor.putString("authToken",loginResponse.authToken)
                                editor.apply()
                                checkUser(loginResponse.authToken)
                            }

                        }


                    }

                    override fun onFailure(call: Call<resVerify?>, t: Throwable) {
                        Toast.makeText(this@Otp_verification, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
        else
        {
           /* var intent = Intent(applicationContext, Otp_verification::class.java)

            intent.putExtra("message","Enter the correct OTP")
            intent.putExtra("phone2",phone)
            startActivity(intent)
            finish()*/

            Toast.makeText(this.applicationContext,"Invalid OTP",Toast.LENGTH_SHORT).show()
            verifyBtn.visibility = View.VISIBLE
            anime.visibility = View.GONE
            editText6!!.setBackgroundResource(R.drawable.user_detail_box)
            editText1!!.text.clear()
            editText2!!.text.clear()
            editText3!!.text.clear()
            editText4!!.text.clear()
            editText5!!.text.clear()
            editText6!!.text.clear()
            editText1!!.requestFocus()
            editText1!!.setBackgroundResource(R.drawable.number_bg_on_click)
            setupOtpInput()


        }

    }

    private fun checkUser(authToken: String?) {
        val header = HashMap<String,String>()
        header["authToken"] = authToken!!
        header["Cookie"] = "JSESSIONID=17D464BCCAB458C440F98723E9F1F208"
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stage.terrafin.tech:8090/").build()
            .create(RetrofitApi::class.java)
        val retrofitData = retrofitBuilder.fetchUser(header)
        retrofitData!!.enqueue(object : Callback<dataUser?>
        {
            override fun onResponse(call: Call<dataUser?>, response: Response<dataUser?>) {
                val userRes = response.body()
                if(response.code() == 200)
                {
                    if(userRes != null)
                    {
                        if(userRes.pan == null || userRes.fatherName == null || userRes.dob == null)

                        {
                            var intent = Intent(applicationContext, user_details::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            var intent = Intent(applicationContext, RefreshPage::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this@Otp_verification,"Sorry! Couldn't Verrify",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<dataUser?>, t: Throwable) {
                var intent = Intent(applicationContext, error_page::class.java)
                startActivity(intent)
                finish()
            }


        })

    }


    fun setupOtpInput() {          //text watcher for all the boxes when number is entered


        val textWatcher1 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()) {
                    editText2!!.requestFocus()
                    editText2!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    editText1!!.setBackgroundResource(R.drawable.user_detail_box)
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
                    editText3!!.requestFocus()
                    editText3!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    editText2!!.setBackgroundResource(R.drawable.user_detail_box)
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
                    editText4!!.requestFocus()
                    editText4!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    editText3!!.setBackgroundResource(R.drawable.user_detail_box)
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
                    editText5!!.requestFocus()
                    editText5!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    editText4!!.setBackgroundResource(R.drawable.user_detail_box)
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
                    editText6!!.requestFocus()
                    editText6!!.setBackgroundResource(R.drawable.number_bg_on_click)
                    editText5!!.setBackgroundResource(R.drawable.user_detail_box)

                }
            }
        }
        val textWatcher6 = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verifyButton()
            }
        }

        editText1!!.addTextChangedListener(textWatcher1)
        editText2!!.addTextChangedListener(textWatcher2)
        editText3!!.addTextChangedListener(textWatcher3)
        editText4!!.addTextChangedListener(textWatcher4)
        editText5!!.addTextChangedListener(textWatcher5)
        editText6!!.addTextChangedListener(textWatcher6)
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


    }

}

