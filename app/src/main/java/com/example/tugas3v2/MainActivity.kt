package com.example.tugas3v2

import android.os.Bundle
import android.widget.TextClock
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)


        val textClock = findViewById<TextClock>(R.id.textClock2)
        textClock.format12Hour = null
        textClock.format24Hour = "HH:mm"

        val textDate = findViewById<TextView>(R.id.textDate)
        updateTextDate()


        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    updateTextDate()
                }
            }
        }, 0, 60000)


    }
    private fun updateTextDate() {
        val currentDate = Date()

        val dateFormat = SimpleDateFormat("E, d MMM", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        val finalText = "$formattedDate"

        findViewById<TextView>(R.id.textDate).text = finalText
    }
}