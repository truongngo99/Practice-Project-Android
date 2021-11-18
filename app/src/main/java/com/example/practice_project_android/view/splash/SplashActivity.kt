package com.example.practice_project_android.view.splash

import SplashViewModel
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice_project_android.R
import com.example.practice_project_android.data.api.ApiService
import com.example.practice_project_android.data.api.RetrofitInstance
import com.example.practice_project_android.data.model.RequestToken
import com.example.practice_project_android.repository.Repository

import com.example.practice_project_android.utils.Constants.Companion.BASE_URL
import com.example.practice_project_android.view.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.concurrent.schedule


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel:SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

      getToken()

    }

    private fun getToken() {


//        val reppo : Repository = Repository()
//
//        val retrfitData = retrofitBuider.getRequestToken()
//        retrfitData.enqueue(object : Callback<RequestToken?> {
//            override fun onResponse(call: Call<RequestToken?>, response: Response<RequestToken?>) {
//                val responseBody=response.body()!!
//                if(responseBody.success){
//                    Timer().schedule(3000){
//                        val intent:Intent = Intent(this@SplashActivity,LoginActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<RequestToken?>, t: Throwable) {
//                Toast.makeText(this@SplashActivity, "Get Token Fail",Toast.LENGTH_SHORT).show()
//            }
//        })
    }


}