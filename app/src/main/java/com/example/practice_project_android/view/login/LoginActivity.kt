package com.example.practice_project_android.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.practice_project_android.R
import com.example.practice_project_android.databinding.ActivityLoginBinding
import com.example.practice_project_android.view.movie.MovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observable()
        binding.btnLogin.setOnClickListener {
            login(binding.edtUsername.text.toString(),binding.edtPassword.text.toString())

        }
        //login()
//        btnLogin.setOnClickListener(View.OnClickListener {
//            var edtUser : String = edtUsername.text.toString()
//            var edtPass : String = edtPassword.text.toString()
//            if(edtUser.isEmpty()){
//                tvErrorUser.visibility = View.VISIBLE
//
//                tvErrorUser.text ="Please enter your name or password"
//
//            } else {
//                tvErrorUser.visibility = View.GONE
//
//            }
//            if (edtPass.isEmpty()){
//                tvErrorPass.visibility = View.VISIBLE
//                tvErrorPass.text ="Please enter your name or password"
//            } else {
//                tvErrorPass.visibility = View.GONE
//            }
//        })
    }
    private fun observable(){
        viewModel.result.observe(this){
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)

        }
        viewModel.loginFaile.observe(this){
            Toast.makeText(this, "Login failure!", Toast.LENGTH_LONG).show()
        }
    }
    private fun login(username: String, password: String) {
        var edtUser : String = binding.edtUsername.text.toString()
            var edtPass : String = binding.edtPassword.text.toString()
            if(edtUser.isEmpty()){
                binding.tvErrorUsername.visibility = View.VISIBLE

                binding.tvErrorUsername.text ="Please enter your name or password"

            } else {
                binding.tvErrorUsername.visibility = View.GONE

            }
            if (edtPass.isEmpty()){
                binding.tvErrorPassword.visibility = View.VISIBLE
                binding.tvErrorPassword.text ="Please enter your name or password"
            } else {
                binding.tvErrorPassword.visibility = View.GONE
            }
        viewModel.login(username,password)
    }
}