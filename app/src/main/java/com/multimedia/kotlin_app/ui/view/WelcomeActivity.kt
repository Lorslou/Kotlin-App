package com.multimedia.kotlin_app.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimedia.kotlin_app.MainActivity
import com.multimedia.kotlin_app.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO el get started vincularía a login si lo aplicase, no a la view del buscador
        binding.btnAccessToApp.setOnClickListener { accessToMainActivity() }
    }

    private fun accessToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}