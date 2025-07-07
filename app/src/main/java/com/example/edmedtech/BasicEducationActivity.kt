package com.example.edmedtech

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edmedtech.adapter.BasicEducationAdapter
import com.example.edmedtech.databinding.ActivityBasicEducationBinding
import com.example.edmedtech.models.BasicEducationList

class BasicEducationActivity : AppCompatActivity() {
    private val binding: ActivityBasicEducationBinding by lazy {
        ActivityBasicEducationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sampleList = listOf(
            BasicEducationList(R.drawable.digitaledu,
                "Digital Library", "Digital books for kids to enhance their reading skills.",
                "Class: Nursery-5",
                "Download →"),
            BasicEducationList(R.drawable.ncert_solution,
                "NCERT Books & Solutions",
                "NCERT Books & Solutions is a free app where you can download and read offline and Ncert.....",
                "Class: 9-10",
                "Download →"),
            BasicEducationList(R.drawable.nect_class,
                "NCERT Class First English",
                "NCERT Class First English Raindrops",
                "Class: 6-8",
                "Download →"),
            BasicEducationList(R.drawable.nusery_class,
                "Nusery Poem Book",
                "Nusery play group poem book",
                "Class: Nursery-5",
                "Download →")
        )

        val adapter = BasicEducationAdapter(sampleList) { item ->
            Toast.makeText(this, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewResources.adapter = adapter
        binding.recyclerViewResources.layoutManager = LinearLayoutManager(this)

    }
}