package com.example.edmedtech

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.databinding.ActivityBrowserBinding

class BrowserActivity : AppCompatActivity() {
    private val binding : ActivityBrowserBinding by lazy {
        ActivityBrowserBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.findDoctorCardView.setOnClickListener {
            startActivity(Intent(this@BrowserActivity, FindDoctorActivity::class.java))
        }
        binding.healthInsuranceCardView.setOnClickListener {
            startActivity(Intent(this@BrowserActivity, HealthInsuranceActivity::class.java))
        }
        binding.educationAndTainingCardView.setOnClickListener {
            startActivity(Intent(this@BrowserActivity, EducationAndTrainingActivity::class.java))
        }
        binding.blogCardView.setOnClickListener {
            startActivity(Intent(this@BrowserActivity, BlogActivity::class.java))
        }


    }
}