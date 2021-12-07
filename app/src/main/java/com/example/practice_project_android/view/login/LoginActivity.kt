package com.example.practice_project_android.view.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_project_android.databinding.ActivityLoginBinding
import com.example.practice_project_android.view.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observable()
        binding.btnLogin.setOnClickListener {

            hideKeyboard()
            login(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
        }
    }

    private fun Activity.hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        currentFocus?.clearFocus()
    }

    private fun observable() {
        viewModel.result.observe(this) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        viewModel.loginFailure.observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnLogin.visibility = View.VISIBLE
            Toast.makeText(this, "Login failure!", Toast.LENGTH_LONG).show()
        }
    }

    private fun login(username: String, password: String) {
        if (validate(username, password)) {
            viewModel.login(username, password)
        }
    }

    private fun validate(username: String, password: String): Boolean {
        var isValidate = true
        binding.btnLogin.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        if (username.isEmpty()) {
            binding.edtUsername.error = "Please enter your name"
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            isValidate = false
        }
        if (password.isEmpty()) {
            binding.edtPassword.error = "Please enter your password"
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            isValidate = false
        }
        return isValidate
    }
}
