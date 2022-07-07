package com.example.drcreditdev.creditCardUI.error

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCardUI.userDetailPageUI.User_details


class Error_page : AppCompatActivity() {
    lateinit var sharedPreferences:SharedPreferences
    lateinit var btnRetry : Button
    lateinit var anime : LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_page)
        btnRetry = findViewById(R.id.btnRetry)
        anime = findViewById(R.id.animeRetry)
        anime.playAnimation()
        btnRetry.setOnClickListener(View.OnClickListener{
           sharedPreferences = getSharedPreferences("drFile", MODE_PRIVATE)
            var intent = Intent(applicationContext, User_details::class.java)
            intent.putExtra("msg","Please enter the correct details")
            startActivity(intent)
            finish()
        })
    }
}