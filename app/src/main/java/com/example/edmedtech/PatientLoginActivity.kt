package com.example.edmedtech

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.databinding.ActivityPatientBinding
import com.google.firebase.auth.FirebaseAuth

class PatientLoginActivity : AppCompatActivity() {

    private val binding: ActivityPatientBinding by lazy {
        ActivityPatientBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Optional animation
        binding.lottieAnimation.playAnimation()

        // Go to Doctor login
        binding.btnDoctorin.setOnClickListener {
            startActivity(Intent(this, DoctorLoginActivity::class.java))
        }

        // Go to SignUp screen
        binding.txtCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        // Handle SignIn
        binding.btnSignin.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                showAlert("All fields are required!")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showAlert("Please enter a valid email address.")
            } else if (password.length < 6) {
                showAlert("Password must be at least 6 characters long.")
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Optional: show short success feedback
                            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()

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
            .setTitle("Validation Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
