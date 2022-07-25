package com.example.drcreditdev.creditCardUI.refreshPageUI

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.drcreditdev.creditCardUI.creditPage.Credit_home_page

import com.example.drcreditdev.creditCardUI.error.Error_page

import com.example.drcreditdev.databinding.ActivityRefreshPageBinding
import com.example.drcreditdev.services.ApiClientRefresh
import java.util.*

class RefreshPage : AppCompatActivity() {
    lateinit var dayS :String
    lateinit var monthS :String
    lateinit var yearS :String

    var diff : String? = null
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var dataBinding : ActivityRefreshPageBinding
    var viewModel: RefreshPageViewModel = RefreshPageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityRefreshPageBinding.inflate(layoutInflater)

        setContentView(dataBinding.root)
        dataBinding.viewModel = viewModel
        viewModel = ViewModelProvider(this).get(RefreshPageViewModel::class.java)

        changePage()

    }
    private fun changePage() {

        val c = Calendar.getInstance()
        val year: String = c.get(Calendar.YEAR).toString()
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var len = year.length
        yearS = (year.get(len - 1) + "" + year.get(len - 2))
        monthS = when (month) {
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mar"
            3 -> "Apr"
            4 -> "May"
            5 -> "June"
            6 -> "Jul"
            7 -> "Aug"
            8 -> "Sept"
            9 -> "Oct"
            10 -> "Nov"
            11 -> "Dec"
            else -> "none"
        }

        sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("day", day.toString())
        editor.putString("month", monthS)
        editor.putString("year", yearS)
        editor.apply()


        var authToken: String? = sharedPreferences.getString("authToken", "user_token")

        viewModel.callRefreshApi(authToken!!)

        viewModel.intentInt.observe(this, {
            if (it == 1) {
                var editor = sharedPreferences.edit()
                editor.putInt("score", viewModel.score.value!!.toInt())
                editor.apply()
                var intent = Intent(applicationContext, Credit_home_page::class.java)
                intent.putExtra("score", viewModel.score.value.toString())
                intent.putExtra("date", day)
                intent.putExtra("month", monthS)
                intent.putExtra("year", yearS)
                startActivity(intent)
                finish()

            }
            else if (it == 2) {
                var intent = Intent(applicationContext, Error_page::class.java)
                startActivity(intent)
                finish()

            } else {
                var intent = Intent(applicationContext, Error_page::class.java)
                startActivity(intent)
                finish()
            }

        })
    }


      /*  apiClient.getApiService().getRefreshScore(header)!!.enqueue(object : Callback<ExampleJson2KtKotlin?>
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

        })*/


}
