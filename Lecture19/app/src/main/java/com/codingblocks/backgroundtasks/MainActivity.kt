package com.codingblocks.backgroundtasks

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var myCalendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            startWorker()
        }

    }

    private fun startWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setConstraints(constraints)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueue(request)

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
            myCalendar.get(Calendar.MINUTE), false
        )

        datePickerDialog.show()
    }

    private fun updateTimeLabel() {
        val format = "HH mm"
        val sdf = SimpleDateFormat(format, Locale.US)
        setNotification()
//        setText(sdf.format(myCalendar.time))    }
    }

    private fun setNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val i = Intent(this, CallReceiver::class.java)
        i.putExtra("NAME", "Alarm Manager")

        val pedingIntent = PendingIntent.getBroadcast(
            this, 0, i, PendingIntent.FLAG_ONE_SHOT
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            myCalendar.timeInMillis,
            pedingIntent
        )
    }

    fun updateLabel() {
        val format = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
//       setText(sdf.format(myCalendar.time))
    }
}
