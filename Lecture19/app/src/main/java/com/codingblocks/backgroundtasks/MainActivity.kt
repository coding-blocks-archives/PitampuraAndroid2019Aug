package com.codingblocks.backgroundtasks

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var myCalendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(
            CallReceiver(),
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    fun setDate() {
        myCalendar = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        val datePickerDialog = DatePickerDialog(
            this, dateListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    fun setTime() {
        myCalendar = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener() { _, hour, min ->

            myCalendar.set(Calendar.HOUR_OF_DAY, hour)
            myCalendar.set(Calendar.MINUTE, min)
            updateTimeLabel()
        }

        val datePickerDialog = TimePickerDialog(
            this, timeListener,
            myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),false
        )

        datePickerDialog.show()
    }

    private fun updateTimeLabel() {
        val format = "HH mm"
        val sdf = SimpleDateFormat(format, Locale.US)
//        setText(sdf.format(myCalendar.time))    }

    fun updateLabel(){
        val format = "EEE, d MMM yyyy"
       val sdf = SimpleDateFormat(format, Locale.US)
//       setText(sdf.format(myCalendar.time))
    }
}
