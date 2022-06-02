package com.example.drcreditdev.creditCard

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.drcreditdev.R
import com.triggertrap.seekarc.SeekArc

class credit_home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var sharedpreferences : SharedPreferences
        lateinit var btnGet : Button
        lateinit var scoreTv : TextView
        lateinit var comment : TextView
        lateinit var arc : SeekArc
        sharedpreferences = getSharedPreferences("drFile", MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_home_page)
        var score = intent.getStringExtra("score")
        btnGet = findViewById(R.id.btnGet)
        scoreTv = findViewById(R.id.tvScore)
        comment = findViewById(R.id.comment)
        arc = findViewById(R.id.seekArc)

        if(score !=null) {
            var scr: Int = score.toInt()
            scoreTv.text = score.toString().trim()
            if(scr>=801)
            {
               comment.text = "Excellent"
                arc.setProgressColor(Color.parseColor("#006400"))
            }
            else if(scr>=761&&scr<=800)
            {
                comment.text = "Good"
                arc.setProgressColor(Color.parseColor("#228B22"))
            }
            else if(scr>=701&&scr<=760)
            {
                comment.text = "Fair"
                arc.setProgressColor(Color.parseColor("#FFD700"))

            }
            else if(scr>=601&&scr<=700)
            {
                comment.text = "Low"
                arc.setProgressColor(Color.parseColor("#FFA500"))
            }
            else if(scr>=300&&scr<=600)
            {
                comment.text = "Very Low"
                arc.setProgressColor(Color.parseColor("#ff0000"))
            }

            var minus: Int = scr-300
            var div : Int = minus/600
            var percent : Int = div*100
            arc.progress = percent
            val editor = sharedpreferences.edit()
            editor.putInt("score",scr)
            editor.apply()
        }
        else
        {
            val status = sharedpreferences.getInt("score",756)
            scoreTv.text = status.toString()
        }
        btnGet.setOnClickListener(View.OnClickListener{

            var intent = Intent(applicationContext,procedding_page::class.java)
            startActivity(intent)
            finish()


        })


    }
}