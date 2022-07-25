package com.example.drcreditdev.creditCardUI.creditPage

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCardUI.refreshPageUI.RefreshPage
import com.triggertrap.seekarc.SeekArc

class Credit_home_page : AppCompatActivity() {
    lateinit var imgBack : ImageView
    lateinit var date: TextView
    lateinit var comment : TextView
    lateinit var arc : SeekArc
    lateinit var sharedpreferences : SharedPreferences
    lateinit var btnGet : Button
    lateinit var scoreTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {




        sharedpreferences = getSharedPreferences("drFile", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_home_page)
        date = findViewById(R.id.date)
        var score = intent.getStringExtra("score")
        btnGet = findViewById(R.id.btnGet)
        scoreTv = findViewById(R.id.tvScore)
        comment = findViewById(R.id.comment)
        arc = findViewById(R.id.seekArc)
        imgBack=findViewById(R.id.imgBack)

        if(score !=null) {
            var scr: Int = score.toInt()
            scoreTv.text = score.toString().trim()
            updateUi(score.toInt())


            var percent : Int = (((score.toInt()-300).toDouble() / 600) * 100).toInt()
            arc.progress = percent
            val editor = sharedpreferences.edit()
            editor.putInt("score",scr)
            editor.putInt("progress",percent)
            editor.putBoolean("isLoggedIn",true)
            editor.apply()
        }
        else
        {
            val status = sharedpreferences.getInt("score",602)
            scoreTv.text = status.toString()
            updateUi(status)
            var percent : Int = (((status-300).toDouble() / 600) * 100).toInt()
            arc.progress = percent

        }
        if(intent.getStringExtra("day")!=null
            &&intent.getStringExtra("month")!=null
            &&intent.getStringExtra("year")!=null)
        {

          date.text = intent.getStringExtra("day").toString()+" "+intent.getStringExtra("month")+"'"+intent.getStringExtra("year")
        }
        else
        {
            date.text=sharedpreferences.getString("day","12").toString()+" "+sharedpreferences.getString("month","Apr").toString()+"'"+sharedpreferences.getString("year","22").toString()
        }
        btnGet.setOnClickListener(View.OnClickListener{

            var intent = Intent(applicationContext, RefreshPage::class.java)
            startActivity(intent)
            finish()


        })
        imgBack.setOnClickListener(View.OnClickListener {

            finish()
        })


    }

    private fun updateUi(scr:Int) {
        if(scr>=801)
        {
            comment.text = "Excellent"
            comment.setTextColor(parseColor("#006400"))
            arc.progressColor = parseColor("#006400")
        }
        else if(scr>=761&&scr<=800)
        {
            comment.text = "Good"
            comment.setTextColor(parseColor("#228B22"))
            arc.progressColor = parseColor("#228B22")
        }
        else if(scr>=701&&scr<=760)
        {
            comment.text = "Fair"
            comment.setTextColor(parseColor("#FFD700"))
            arc.progressColor = parseColor("#FFD700")

        }
        else if(scr>=601&&scr<=700)
        {
            comment.text = "Low"
            comment.setTextColor(parseColor("#FFA500"))
            arc.progressColor = parseColor("#FFA500")
        }
        else if(scr>=300&&scr<=600)
        {
            comment.text = "Very Low"
            comment.setTextColor(parseColor("#ff0000"))
            arc.progressColor = parseColor("#ff0000")
        }

    }
}