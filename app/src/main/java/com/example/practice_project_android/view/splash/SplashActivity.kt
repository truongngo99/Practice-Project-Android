package com.example.practice_project_android.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.practice_project_android.R

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
   private val viewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)
        observable()
        loadData()

    }
    private fun observable(){
        viewModel.result.observe(this){
            findViewById<TextView>(R.id.tv_copyright).text = it.request_token
        }
    }
    private fun loadData() {
        viewModel.getToken()
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