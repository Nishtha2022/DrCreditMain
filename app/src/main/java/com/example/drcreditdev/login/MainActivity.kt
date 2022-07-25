package com.example.drcreditdev.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCardUI.creditPage.Credit_home_page
import com.example.drcreditdev.databinding.ActivityMainBinding
import com.example.drcreditdev.login.userNumberUI.UserNumber
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedpreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        sharedpreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        val status = sharedpreferences.getBoolean("isLoggedIn",false)
        setContentView(binding.root)
        var timer = Timer()
        timer.schedule(object : TimerTask() {

            override fun run() {
                if(status == true)
                {
                    var intent = Intent(applicationContext,  Credit_home_page::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    var intent = Intent(applicationContext, UserNumber::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 2000)
    }
}