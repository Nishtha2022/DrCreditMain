package com.example.drcreditdev.creditCard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.drcreditdev.R
import com.example.drcreditdev.dataModal.SessionManager
import com.example.drcreditdev.dataModal.reqCreditScore
import com.example.drcreditdev.services.ApiClientCredit
import com.example.example.ExampleJson2KtKotlin
import com.example.example.ScoreDetails
import java.util.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class procedding_page : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClientCredit
    lateinit var anime : LottieAnimationView
    lateinit var pan:String
    lateinit var fullName:String
    lateinit var fatherName:String
    lateinit var dob:String
    lateinit var state:String
    lateinit var address :String
    lateinit var pinCode:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_procedding_page)
        getCreditScore()
        anime = findViewById(R.id.anime)
        anime.playAnimation()






    }
    private fun getCreditScore()
    {
        if(intent.getStringExtra("pan")!=null
            &&intent.getStringExtra("fullName")!=null
            &&intent.getStringExtra("fatherName")!=null
            &&intent.getStringExtra("dob")!=null
            &&intent.getStringExtra("state")!=null
            &&intent.getStringExtra("address")!=null
            &&intent.getStringExtra("pinCode")!=null) {
             pan = intent.getStringExtra("pan").toString()
             fullName = intent.getStringExtra("fullName").toString()
             fatherName = intent.getStringExtra("fatherName").toString()
             dob = intent.getStringExtra("dob").toString()
             state = intent.getStringExtra("state").toString()
             address = intent.getStringExtra("address").toString()
             pinCode = intent.getStringExtra("pinCode").toString()
        }
        else{
            sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
            pan = sharedPreferences.getString("pan","null")!!
            fullName=sharedPreferences.getString("fullName","null")!!
            fatherName=sharedPreferences.getString("fatherName","null")!!
            dob = sharedPreferences.getString("dob","null")!!
            pinCode= sharedPreferences.getString("pinCode","null")!!
            state = sharedPreferences.getString("state","null")!!
            address = sharedPreferences.getString("address","null")!!
         /*   if(sharedPreferences.getString("diff","null")!=null)
            {
                var diff = sharedPreferences.getString("diff","null")
            }*/


        }
        apiClient = ApiClientCredit()
        sessionManager = SessionManager(this@procedding_page)
        sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        var authToken:String? = sharedPreferences.getString("authToken","user_token")
        val header = HashMap<String, String>()
        header["authToken"] = authToken!!
        header["Content-Type"] = "application/json"
        header["Cookie"] = "JSESSIONID=F7AC6F52A47B66466769E1EFE88BBA9B"

        apiClient.getApiService().fetchCreditScore(header,reqCreditScore(fullName, dob,pinCode,state,pan,fatherName,address))!!
            .enqueue(object : Callback<ExampleJson2KtKotlin?>{

                override fun onResponse(
                    call: Call<ExampleJson2KtKotlin?>,
                    response: Response<ExampleJson2KtKotlin?>
                ) {
                    var creditRes = response.body()
                    if(response.code()==200)
                    {

                        if (creditRes != null) {
                            Toast.makeText(this@procedding_page,"succedd",Toast.LENGTH_LONG).show()

                            try {
                                if(creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!=null) {
                                    var score: String =
                                        (creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)!!.toString()
                                            .trim()
                                    if (score != null && score != "-1") {
                                        sharedPreferences =
                                            getSharedPreferences("drFile", MODE_PRIVATE)
                                        val editor = sharedPreferences.edit()
                                        editor.putBoolean("isLoggedIn",true)
                                        editor.putString("pan", pan)
                                        editor.putString("fullName", fullName)
                                        editor.putString("fatherName", fatherName)
                                        editor.putString("dob", dob)
                                        editor.putString("pinCode", pinCode)
                                        editor.putString("state", state)
                                        editor.putString("address", address)
                                        editor.putString("score", score)
                                        var intent =
                                            Intent(applicationContext, credit_home_page::class.java)
                                        intent.putExtra("score", score)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else
                                    {
                                        var intent = Intent(applicationContext, error_page::class.java)
                                        startActivity(intent)
                                        finish()

                                    }
                                }

                                else
                                {
                                    var intent = Intent(applicationContext, error_page::class.java)
                                    startActivity(intent)
                                    finish()
                                }

                            }
                            catch (e:Exception){
                                var intent = Intent(applicationContext, error_page::class.java)
                                startActivity(intent)
                                finish()

                            }
                            /*if(((creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value).toString()=="-1"
                                ||  (creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)==null))
                            {
                                var intent = Intent(applicationContext, error_page::class.java)
                                startActivity(intent)
                                finish()

                            }
                            else {


                                var score: String =
                                    (creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)!!.toString()
                                        .trim()
                                sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("pan", pan)
                                editor.putString("fullName", fullName)
                                editor.putString("fatherName", fatherName)
                                editor.putString("dob", dob)
                                editor.putString("pinCode", pinCode)
                                editor.putString("state", state)
                                editor.putString("address", address)
                                editor.putString("score", score)
                                var intent =
                                    Intent(applicationContext, credit_home_page::class.java)
                                intent.putExtra("score", score)
                                startActivity(intent)
                                finish()
                            }
                            */


                        }

                    }
                    else
                    {
                        var intent = Intent(applicationContext, error_page::class.java)
                        startActivity(intent)
                        finish()
                    }

                }

                override fun onFailure(call: Call<ExampleJson2KtKotlin?>, t: Throwable) {
                    Toast.makeText(this@procedding_page,t.message,Toast.LENGTH_SHORT).show()
                    var intent = Intent(applicationContext,error_page::class.java)
                    startActivity(intent)
                    finish()


                }


            } )


    }


}