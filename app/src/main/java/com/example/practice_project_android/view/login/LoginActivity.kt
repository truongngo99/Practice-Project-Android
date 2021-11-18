package com.example.practice_project_android.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.practice_project_android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin : Button = findViewById(R.id.btn_login)
        val tvErrorUser : TextView = findViewById(R.id.tv_error_username)
        val tvErrorPass : TextView = findViewById(R.id.tv_error_password)
        val edtUsername : EditText = findViewById(R.id.edt_username)
        val edtPassword : EditText = findViewById(R.id.edt_password)

        btnLogin.setOnClickListener(View.OnClickListener {
            var edtUser : String = edtUsername.text.toString()
            var edtPass : String = edtPassword.text.toString()
            if(edtUser.isEmpty()){
                tvErrorUser.visibility = View.VISIBLE

                tvErrorUser.text ="Chua nhap username"

            } else {
                tvErrorUser.visibility = View.GONE

            }
            if (edtPass.isEmpty()){
                tvErrorPass.visibility = View.VISIBLE
                tvErrorPass.text ="Chua nhap username"
            } else {
                tvErrorPass.visibility = View.GONE
            }
        })
    }
}