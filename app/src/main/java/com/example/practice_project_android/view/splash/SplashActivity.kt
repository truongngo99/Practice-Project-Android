package com.example.practice_project_android.view.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_project_android.R
import com.example.practice_project_android.view.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)
        loadData()
        observable()

    }


    private fun observable() {
        viewModel.result.observe(this) {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModel.failure.observe(this){
            Toast.makeText(this, "Get Token Failure", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadData() {
        viewModel.getToken()
    }


}