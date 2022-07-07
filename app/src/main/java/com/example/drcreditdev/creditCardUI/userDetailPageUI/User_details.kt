package com.example.drcreditdev.creditCardUI.userDetailPageUI

import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drcreditdev.R
import com.example.drcreditdev.creditCardUI.proceedingPageUI.Procedding_page
import com.example.drcreditdev.databinding.ActivityUserDetailsBinding
import com.example.drcreditdev.util.toast


class User_details : AppCompatActivity() {

    lateinit var binding : ActivityUserDetailsBinding //= ActivityUserDetailsBinding.inflate(layoutInflater)
    var viewModel : UserDetailViewModel= UserDetailViewModel()//= ViewModelProvider(this).get(UserDetailViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        var array = ArrayAdapter(this@User_details,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.State))
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.etState.adapter = array

        binding.etPan.addTextChangedListener(textWatcher)
        binding.etFullName.addTextChangedListener(textWatcher)
        binding.etFatherName.addTextChangedListener(textWatcher)
        binding.etDate.addTextChangedListener(textWatcher)
        binding.etAddress.addTextChangedListener(textWatcher)
        binding.etPincode.addTextChangedListener(textWatcher)



        binding.btnConfirm.setOnClickListener(View.OnClickListener {

            viewModel.pan = binding.etPan.text.toString()
            viewModel.fullName = binding.etFullName.text.toString()
            viewModel.fatherName = binding.etFatherName.text.toString()
            viewModel.dob = binding.etDate.text.toString()
            viewModel.address = binding.etAddress.text.toString()
            viewModel.state = binding.etState.selectedItem.toString()
            viewModel.pinCode = binding.etPincode.text.toString()
            viewModel.OnClickProceedBtn()
            var toastM = viewModel.setToast.value
            if(toastM == 1)
            {
                toast("Enter all Details")
            }
            else if(toastM == 2)
            {
                toast("Enter Correct PAN Number")
            }
            else if(toastM == 3)
            {

                toast("Enter Correct Pin Code")
            }
            else if(toastM == 4)
            {

                visitNextPage()
            }

        })

        binding.etDate.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year1 = calendar.get(Calendar.YEAR)
            val month1 = calendar.get(Calendar.MONTH)
            val day1 = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(this@User_details, DatePickerDialog.OnDateSetListener
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
                    else if(monthOfYear<9 && dayOfMonth<10)
                    {
                        binding.etDate.setText(""+"0"+dayOfMonth+"/"+"0"+(monthOfYear+1)+"/"+year)
                    }

                    else if(monthOfYear<9&&dayOfMonth>10) {
                        binding.etDate.setText("" + dayOfMonth + "/" +"0"+ (monthOfYear + 1) + "/" + year)
                    }
                    else if(dayOfMonth<10&&monthOfYear>=9)
                    {
                        binding.etDate.setText(""+"0"+dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
                    }
                    else
                    {
                        binding.etDate.setText("" + dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year)
                    }

                }, year1, month1, day1
                )
            datePickerDialog.show()

        })// add validation to date

    }

    // proceeding page visit

    private fun visitNextPage() {

       var stateCode  = when(binding.etState.selectedItem.toString()){
            "Andhra Pradesh" -> "AP"
            "Arunachal Pradesh"->"AR"
            "Assam"-> "AS"
            "Bihar"->"BR"
            "Chandigarh"->"CH"
            "Chhattisgarh"->"CT"
            "Dadra and Nagar Haveli"->"DN"
            "Daman and Diu"->"DD"
            "Delhi"->"DL"
            "Goa"->"GA"
            "Gujarat"->"GJ"
            "Haryana"-> "HR"
            "Himachal Pradesh"->"HP"
            "Jammu and Kashmir"->"JK"
            "Jharkhand"->"JH"
            "Karnataka"->"KA"
            "Kerala"->"KL"
            "Lakshadweep"->"LD"
            "Madhya Pradesh"->"MP"
            "Maharashtra"->"MH"
            "Manipur"->"MN"
            "Meghalaya"->"ML"
            "Mizoram"->"MZ"
            "Nagaland"->"NL"
            "Odisha"->"OR"
            "Puducherry"->"PY"
            "Punjab"->"PB"
            "Rajasthan"->"RJ"
            "Sikkim"->"SK"
            "Tamil Nadu"->"TN"
            "Telangana"->"TG"
            "Tripura"->"TR"
            "Uttar Pradesh"->"UP"
            "Uttarakhand"->"UT"
            "West Bengal"->"WB"
            else -> "none"

        }

        var intent = Intent(applicationContext, Procedding_page::class.java)
        intent.putExtra("pan",binding.etPan.text.toString().trim())
        intent.putExtra("fullName",binding.etFullName.text.toString().trim())
        intent.putExtra("fatherName",binding.etFatherName.text.toString().trim())
        intent.putExtra("dob",binding.etDate.text.toString().trim())
        intent.putExtra("state",stateCode)
        intent.putExtra("address",binding.etAddress.text.toString().trim())
        intent.putExtra("pinCode",binding.etPincode.text.toString().trim())
        startActivity(intent)
        finish()

    }


    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if(((binding.etPan.text.toString().length == 0) || (binding.etFatherName.text.toString().length == 0) ||
                        (binding.etDate.text.toString().length == 0) || (binding.etAddress.text.toString().length == 0) ||
                        (binding.etFullName.text.trim().length == 0) || (binding.etPincode.text.toString().length == 0))){
                binding.btnConfirm.isEnabled = false
            }
            else
            {
                binding.btnConfirm.isEnabled = true
                binding.btnConfirm.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,
                    R.color.button, theme))
                binding.btnConfirm.setTextColor(Color.parseColor("#FFFFFF"))
            }

        }


    }


}
