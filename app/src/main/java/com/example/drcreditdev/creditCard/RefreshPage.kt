package com.example.drcreditdev.creditCard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drcreditdev.R
import com.example.drcreditdev.services.ApiClientRefresh
import com.example.drcreditdev.services.RetrofitApi
import com.example.example.ExampleJson2KtKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.HashMap

class RefreshPage : AppCompatActivity() {
    lateinit var dayS :String
    lateinit var monthS :String
    lateinit var yearS :String

    var diff : String? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiClient: ApiClientRefresh
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_page)
        sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        var authToken:String? = sharedPreferences.getString("authToken","user_token")
        val header = HashMap<String, String>()
        header["authToken"] = authToken!!
        header["Content-Type"] = "application/json"
        header["Cookie"] = "JSESSIONID=F7AC6F52A47B66466769E1EFE88BBA9B"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stage.terrafin.tech:8090/underwriting/").build()
            .create(RetrofitApi::class.java)

        val retrofitData = retrofitBuilder.getRefreshScore(header)
        val c = Calendar.getInstance()
        val year : String = c.get(Calendar.YEAR).toString()
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

          var len = year.length
          yearS = (year.get(len-1)+""+year.get(len-2))
        monthS = when(month){
            0 ->"Jan"
            1->"Feb"
            2->"Mar"
            3->"Apr"
            4->"May"
            5->"June"
            6->"Jul"
            7->"Aug"
            8->"Sept"
            9->"Oct"
            10->"Nov"
            11->"Dec"
            else -> "none"
        }
        var editor = sharedPreferences.edit()
        editor.putString("day",day.toString())
        editor.putString("month",monthS)
        editor.putString("year",yearS)
        editor.apply()




        retrofitData!!.enqueue(object : Callback<ExampleJson2KtKotlin?>
        {
            override fun onResponse(
                call: Call<ExampleJson2KtKotlin?>,
                response: Response<ExampleJson2KtKotlin?>
            ) {
                var creditRes = response.body()
                if(response.code()==200)
                {
                        if(creditRes!!.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!=null) {
                            var score: String =
                                (creditRes.payload?.ccrresponse?.cirreportDataLst!![0].cirreportData!!.scoreDetails[0].value)!!.toString()
                            // sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
                            //   var lastScore = sharedPreferences.getString("score","300")

                            /*       if(lastScore.equals(score)==false)
                        {
                            var differnce = score.toInt()-lastScore!!.toInt()
                            var editor = sharedPreferences.edit()
                            editor.putString("score",score)
                            editor.apply()
                            diff = differnce.toString()

                        }*/

                            var editor = sharedPreferences.edit()
                            editor.putInt("score",score.toInt())
                            editor.apply()

                            var intent = Intent(applicationContext, credit_home_page::class.java)
                            intent.putExtra("score", score)
                            intent.putExtra("date", day)
                            intent.putExtra("month", monthS)
                            intent.putExtra("year", yearS)

                            /*    if(diff!=null)
                        {
                            intent.putExtra("diff",diff)
                        }*/
                            startActivity(intent)
                            finish()

                        }
                        else{
                            var intent = Intent(applicationContext, error_page::class.java)
                            startActivity(intent)
                            finish()



                        }

                    }
                else{
                    var intent = Intent(applicationContext, error_page::class.java)
                    startActivity(intent)
                    finish()


                }
            }

            override fun onFailure(call: Call<ExampleJson2KtKotlin?>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
                var intent = Intent(applicationContext, error_page::class.java)
                startActivity(intent)
                finish()

            }

        })

    }
}
