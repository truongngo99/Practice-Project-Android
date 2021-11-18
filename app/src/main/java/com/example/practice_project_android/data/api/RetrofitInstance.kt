package com.example.practice_project_android.data.api

import com.example.practice_project_android.utils.Constants
import com.example.practice_project_android.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    companion object{
        val apiService =  Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build().create(ApiService::class.java)
    }

}


