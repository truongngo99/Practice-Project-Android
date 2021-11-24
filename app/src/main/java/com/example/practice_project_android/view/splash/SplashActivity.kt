package com.example.practice_project_android.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
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
        observable()
        loadData()

    }

    private fun observable() {
        viewModel.result.observe(this) {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        viewModel.getToken()
    }

    private fun getToken() {

    }


}