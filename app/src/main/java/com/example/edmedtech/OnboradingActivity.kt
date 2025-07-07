package com.example.edmedtech

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.databinding.ActivityOnboradingBinding

class OnboradingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboradingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboradingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, PatientLoginActivity::class.java))
        }

    }
}