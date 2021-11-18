package com.example.practice_project_android.repository

import com.example.practice_project_android.data.api.RetrofitInstance
import com.example.practice_project_android.data.model.RequestToken

class Repository {
    suspend fun getRequestToken():RequestToken{
      return  RetrofitInstance.apiService.getRequestToken()
    }
}