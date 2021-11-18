package com.example.practice_project_android.repository

import com.example.practice_project_android.data.api.ApiService
import com.example.practice_project_android.data.model.RequestToken
import javax.inject.Inject

class Repository @Inject constructor( private val apiService: ApiService) {
    suspend fun getRequestToken():RequestToken{
      return  apiService.getRequestToken()
    }
}