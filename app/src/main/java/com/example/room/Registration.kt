package com.example.room

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText

import com.example.room.models.DatabaseProvider
import com.example.room.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val firstNameEditText: EditText = findViewById(R.id.etFirstName)
        val lastNameEditText: EditText = findViewById(R.id.etLastName)
        val emailEditText: EditText = findViewById(R.id.etEmail)
        val passwordEditText: EditText = findViewById(R.id.etPassword)
        val confirmPasswordEditText: EditText = findViewById(R.id.etConfirmPassword)
        val registerButton: Button = findViewById(R.id.btnRegister)

        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                val user = User(firstName = firstName, lastName = lastName, email = email, password = password)

                CoroutineScope(Dispatchers.IO).launch {
                    DatabaseProvider.getDatabase(applicationContext).userDao().insert(user)
                }

                // Inform the user of successful registration or navigate to another activity
            } else {
                // Inform the user that passwords do not match
            }
        }
    }
}
