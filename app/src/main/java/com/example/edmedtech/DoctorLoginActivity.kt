package com.example.edmedtech

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.databinding.ActivityDoctorBinding
import com.google.firebase.auth.FirebaseAuth

class DoctorLoginActivity : AppCompatActivity() {

    private val binding: ActivityDoctorBinding by lazy {
        ActivityDoctorBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Go to Sign Up screen
        binding.txtCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        // Login button logic
        binding.btnDoctorin.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                showAlert("All fields are required!")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showAlert("Please enter a valid email address.")
            } else if (password.length < 6) {
                showAlert("Password must be at least 6 characters long.")
            } else {
                // Login with Firebase
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Optional: show short success feedback
                            Toast.makeText(this, "Welcome back, Doctor!", Toast.LENGTH_SHORT).show()

                            // Go to main screen
                            startActivity(Intent(this, BrowserActivity::class.java)) // Replace with actual target
                            finish()
                        } else {
                            // Show login failure
                            AlertDialog.Builder(this)
                                .setTitle("Login Failed")
                                .setMessage("Authentication Failed: ${task.exception?.message}")
                                .setPositiveButton("OK", null)
                                .show()
                        }
                    }
            }
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
