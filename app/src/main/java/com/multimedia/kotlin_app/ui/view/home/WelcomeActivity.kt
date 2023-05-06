package com.multimedia.kotlin_app.ui.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimedia.kotlin_app.databinding.ActivityWelcomeBinding
import com.multimedia.kotlin_app.ui.view.AgentSearchViewActivity

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
        val intent = Intent(this, AgentSearchViewActivity::class.java)
        startActivity(intent)
    }

    private fun loadAgentsToDb() {
        
    }
}