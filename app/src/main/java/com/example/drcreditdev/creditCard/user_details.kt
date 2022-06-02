package com.example.drcreditdev.creditCard

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import com.example.drcreditdev.R


class user_details : AppCompatActivity() {
    lateinit var sharedpreferences : SharedPreferences
    lateinit var btnConfirm : Button
    lateinit var etFullName : EditText
    lateinit var etFatherName : EditText
    lateinit var etPan : EditText
    lateinit var etDate : EditText
    lateinit var imgCal : ImageView
    lateinit var etPincode : EditText
    lateinit var etAddress : EditText
    lateinit var etState : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        btnConfirm = findViewById(R.id.btnConfirm)
        etDate = findViewById(R.id.etDate)
        etFatherName = findViewById(R.id.etFatherName)
        etFullName = findViewById(R.id.etFullName)
        etPan = findViewById(R.id.etPan)
        etPincode = findViewById(R.id.etPincode)
        etState = findViewById(R.id.etState)
        etAddress = findViewById(R.id.etAddress)
        var array = ArrayAdapter(this@user_details,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.State))
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        etState.adapter = array

        etPan.addTextChangedListener(textWatcher)
        etFullName.addTextChangedListener(textWatcher)
        etFatherName.addTextChangedListener(textWatcher)
        etDate.addTextChangedListener(textWatcher)
        etAddress.addTextChangedListener(textWatcher)
        etPincode.addTextChangedListener(textWatcher)

        btnConfirm.setOnClickListener(View.OnClickListener {

            var panCard = etPan.text.toString().trim()
            var father = etFatherName.text.toString().trim()
            var pinCode = etPincode.text.toString().trim()

            val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
            val matcher: Matcher = pattern.matcher(panCard)
            val pinCodeReg : Pattern = Pattern.compile("[1-9]{1}[0-9]{2}[0-9]{3}")
            val matcher_pan = pinCodeReg.matcher(pinCode)





            if(TextUtils.isEmpty(etPan.toString().trim())
                ||TextUtils.isEmpty(etFullName.toString().trim())
                ||TextUtils.isEmpty(etFatherName.toString().trim())
                ||TextUtils.isEmpty(etDate.toString().trim())
                ||TextUtils.isEmpty(etAddress.toString().trim())
                ||TextUtils.isEmpty(etState.toString().trim())
                ||TextUtils.isEmpty(etPincode.toString().trim()))
            {
                Toast.makeText(this@user_details,"Please fill all the column",Toast.LENGTH_SHORT).show()
            }
            else if(!(matcher.matches())) {

                Toast.makeText(
                    this@user_details,
                    "Please enter correct PAN number",
                    Toast.LENGTH_SHORT
                ).show()

            }
            else if(!(matcher_pan.matches()))
            {
                Toast.makeText(this,"Enter valid Pin Code",Toast.LENGTH_SHORT).show()
            }
            else
            {
                var intent = Intent(applicationContext, procedding_page::class.java)
                intent.putExtra("pan",etPan.text.toString().trim())
                intent.putExtra("fullName",etFullName.text.toString().trim())
                intent.putExtra("fatherName",etFatherName.text.toString().trim())
                intent.putExtra("dob",etDate.text.toString().trim())
                intent.putExtra("state",etState.getSelectedItem().toString())
                intent.putExtra("address",etAddress.text.toString().trim())
                intent.putExtra("pinCode",etPincode.text.toString().trim())
                startActivity(intent)
                finish()
            }

        })
        imgCal = findViewById(R.id.imgCal)
        imgCal.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year1 = calendar.get(Calendar.YEAR)
            val month1 = calendar.get(Calendar.MONTH)
            val day1 = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(this@user_details, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->

                    // if(year>)
                    if(year>year1)
                    {
                        Toast.makeText(this,"Enter the correct date ",Toast.LENGTH_SHORT).show()

                    }
                    else if(year==year1&&month1<monthOfYear)
                    {
                        Toast.makeText(this,"Enter the correct date ",Toast.LENGTH_SHORT).show()

                    }
                    else if(year==year1&& month1==monthOfYear&&day1<dayOfMonth)
                    {
                        Toast.makeText(this,"Enter the correct date ",Toast.LENGTH_SHORT).show()

                    }

                    else if(monthOfYear<10) {
                        etDate.setText("" + dayOfMonth + "/" +"0"+ (monthOfYear + 1) + "/" + year)
                    }
                    else
                    {
                        etDate.setText("" + dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year)
                    }

                }, year1, month1, day1
                )
            datePickerDialog.show()

        })// add validation to date

    }
    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if(((etPan.text.toString().length == 0) || (etFatherName.text.toString().length == 0) ||
                        (etDate.text.toString().length == 0) || (etAddress.text.toString().length == 0) ||
                        (etFullName.text.trim().length == 0) || (etPincode.text.toString().length == 0))){
                btnConfirm.isEnabled = false
            }
            else
            {
                btnConfirm.isEnabled = true
                btnConfirm.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                    R.color.button, theme))
                btnConfirm.setTextColor(Color.parseColor("#FFFFFF"))
            }

        }


    }


}
