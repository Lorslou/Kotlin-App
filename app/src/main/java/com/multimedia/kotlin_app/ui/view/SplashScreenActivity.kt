package com.multimedia.kotlin_app.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimedia.kotlin_app.MainActivity

/**
 * This activity is used to display a brief splash screen or start screen when the application is
 * launched, and then it redirects to the MainActivity
 */
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish() //to prevent it from going back to the splash screen
    }
}