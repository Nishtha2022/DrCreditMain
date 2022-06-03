package com.example.drcreditdev.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCard.credit_home_page
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedpreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedpreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        val status = sharedpreferences.getBoolean("isLoggedIn",false)
        setContentView(R.layout.activity_main)
        var timer = Timer()
        timer.schedule(object : TimerTask() {

            override fun run() {
                if(status == true)
                {
                    var intent = Intent(applicationContext,credit_home_page::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    var intent = Intent(applicationContext, userNumber::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 2000)
    }
}