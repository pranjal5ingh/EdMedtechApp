package com.example.edmedtech

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.databinding.ActivityEducationAndTrainingBinding

class EducationAndTrainingActivity : AppCompatActivity() {
    private val binding : ActivityEducationAndTrainingBinding by lazy {
        ActivityEducationAndTrainingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnBasicEdu.setOnClickListener {
            startActivity(Intent(this, BasicEducationActivity::class.java))
        }
    }
}