package com.example.edmedtech

import DoctorAdapter
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edmedtech.databinding.ActivityFindDoctorBinding
import com.example.edmedtech.models.Doctor

class FindDoctorActivity : AppCompatActivity() {

    private val binding: ActivityFindDoctorBinding by lazy {
        ActivityFindDoctorBinding.inflate(layoutInflater)
    }

    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var allDoctors: List<Doctor> // ✅ Fix 1: declare allDoctors globally

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Specialization dropdown options
        val specializationList = listOf(
            "All", // Show all by default
            "Cardiologist", "Dermatologist", "Dentist", "General Physician",
            "Gynecologist", "Neurosurgeon", "Orthopedic", "Pediatrician", "Psychiatrist", "ENT Specialist"
        )

        val specializationAdapter = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, specializationList
        )
        binding.autoSpecialization.setAdapter(specializationAdapter)

        // Show dropdown when clicked
        binding.autoSpecialization.inputType = 0
        binding.autoSpecialization.keyListener = null
        binding.autoSpecialization.setOnClickListener {
            binding.autoSpecialization.showDropDown()
        }

        // Doctor list
        allDoctors = listOf( // ✅ Fix 2: assign to `allDoctors`
            Doctor(
                R.drawable.femaledo,
                "Dr. Rati Agrawal",
                "MBBS, DNB General Surgery, DNB Neurosurgery, Former " +
                        "Resident AIIMS Delhi, MAMC & LNJP Delhi and Safdarjung " +
                        "Hospital Delhi, Fellowship of Neuroendoscopy NSCB Medical",
                "Neurosurgeon",
                "Exp. 18 years",
                "1500"
            ),
            Doctor(
                R.drawable.maledo,
                "Dr. Sangeet Kumar Agrawal",
                "MBBS, DLO, DNB (ENT & Head & Neck Surgery), Fellowship " +
                        "in Head & Neck Oncology from Medanta Hospital, IFNOS-" +
                        "MSKCC New York",
                "ENT Specialist",
                "Exp. 12 years",
                "1500"
            ),
                    Doctor(
                    R.drawable.femaledo,
            "Dr. Rati Agrawal",
            "MBBS, DNB General Surgery, DNB Neurosurgery, Former " +
                    "Resident AIIMS Delhi, MAMC & LNJP Delhi and Safdarjung " +
                    "Hospital Delhi, Fellowship of Neuroendoscopy NSCB Medical",
            "Neurosurgeon",
            "Exp. 18 years",
            "1500"
        ),
        Doctor(
            R.drawable.maledo,
            "Dr. Sangeet Kumar Agrawal",
            "MBBS, DLO, DNB (ENT & Head & Neck Surgery), Fellowship " +
                    "in Head & Neck Oncology from Medanta Hospital, IFNOS-" +
                    "MSKCC New York",
            "ENT Specialist",
            "Exp. 12 years",
            "1500"
        ),
        Doctor(
            R.drawable.femaledo,
            "Dr. Rati Agrawal",
            "MBBS, DNB General Surgery, DNB Neurosurgery, Former " +
                    "Resident AIIMS Delhi, MAMC & LNJP Delhi and Safdarjung " +
                    "Hospital Delhi, Fellowship of Neuroendoscopy NSCB Medical",
            "Neurosurgeon",
            "Exp. 18 years",
            "1500"
        ),
        Doctor(
            R.drawable.maledo,
            "Dr. Sangeet Kumar Agrawal",
            "MBBS, DLO, DNB (ENT & Head & Neck Surgery), Fellowship " +
                    "in Head & Neck Oncology from Medanta Hospital, IFNOS-" +
                    "MSKCC New York",
            "ENT Specialist",
            "Exp. 12 years",
            "1500"
        )
        )

        // Initialize adapter with full doctor list
        doctorAdapter = DoctorAdapter(allDoctors.toMutableList())
        binding.recyclerViewDoctors.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDoctors.adapter = doctorAdapter

        // Filter doctor list when specialization is selected
        binding.autoSpecialization.setOnItemClickListener { _, _, position, _ ->
            val selected = specializationList[position]
            binding.specializationLayout.hint = ""
            val filtered = if (selected == "All") {
                allDoctors
            } else {
                allDoctors.filter { it.specialization.equals(selected, ignoreCase = true) }
            }

            doctorAdapter.updateList(filtered)

            // 🔥 Change background when selected
            binding.autoSpecialization.setBackgroundResource(R.drawable.bg_selected_dropdown)
        }

    }

}
