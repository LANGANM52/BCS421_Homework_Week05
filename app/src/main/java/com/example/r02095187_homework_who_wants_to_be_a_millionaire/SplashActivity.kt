package com.example.r02095187_homework_who_wants_to_be_a_millionaire

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY_MS: Long = 2000 // 2 seconds delay for the splash screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Set your SplashActivity layout XML here

        // Delay for a few seconds and then transition to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish() // Close the SplashActivity to prevent user from coming back to it
        }, SPLASH_DELAY_MS)
    }
}
