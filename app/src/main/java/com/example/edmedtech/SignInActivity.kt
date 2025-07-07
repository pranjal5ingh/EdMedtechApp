package com.example.edmedtech

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.edmedtech.models.User
import com.example.edmedtech.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtBacktoLogin.setOnClickListener {
            startActivity(Intent(this, PatientLoginActivity::class.java))
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val confirmPassword = binding.confirmpasswordInput.text.toString().trim()


            // Get userType selection
            val selectedUserTypeId = binding.userTypeGroup.checkedRadioButtonId
            val userType = when (selectedUserTypeId) {
                R.id.radioDoctor -> "doctor"
                R.id.radioPatient -> "patient"
                else -> ""
            }
            if (userType.isEmpty()) {
                showAlert("Please select Doctor or Patient.")
                return@setOnClickListener
            }

            // check if any field is blank
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert("All fields are required!")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showAlert("Please enter a valid email address.")
            } else if (password.length < 6) {
                showAlert("Password must be at least 6 characters long.")
            } else if (password != confirmPassword) {
                showAlert("Password and Confirm Password do not match.")
            } else {
                // All validations passed, proceed with registration
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            val user = User(email, password, userType) // Warning: Don't store raw password in production
                            userId?.let {
                                FirebaseDatabase.getInstance().getReference("users")
                                    .child(it)
                                    .setValue(user)
                                    .addOnCompleteListener { dbTask ->
                                        if (dbTask.isSuccessful) {
                                            val nextActivity = if (userType == "doctor") {
                                                DoctorLoginActivity::class.java
                                            } else {
                                                PatientLoginActivity::class.java
                                            }

                                            AlertDialog.Builder(this)
                                                .setTitle("Success")
                                                .setMessage("Registration Successful.")
                                                .setPositiveButton("OK") { _, _ ->
                                                    startActivity(Intent(this, nextActivity))
                                                    finish()
                                                }
                                                .show()

                                        } else {
                                            showAlert("Failed to store user data: ${dbTask.exception?.message}")
                                        }
                                    }
                            }
                        }else {
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                showAlert("This email is already registered. Please log in instead.")
                            } else {
                                showAlert("Registration Failed: ${task.exception?.message}")
                            }

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