package com.example.practice_project_android.data.api

import com.example.practice_project_android.data.model.RequestToken
import com.example.practice_project_android.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("authentication/token/new?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getRequestToken(): RequestToken
}