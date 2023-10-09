package com.example.r02095187_homework_who_wants_to_be_a_millionaire

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats) // Set your StatsActivity layout XML here

        val totalEarnings = intent.getIntExtra("TOTAL_EARNINGS", 0)

        // Display the total earnings in a TextView
        val totalEarningsTextView: TextView = findViewById(R.id.totalEarningsTextView)
        totalEarningsTextView.text = "Total Earnings: $$totalEarnings"
    }
}
