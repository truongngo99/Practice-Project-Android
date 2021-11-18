package com.example.practice_project_android.data.model

data class RequestToken(
    val success: Boolean,
    val expires_at:String,
    val request_token:String,
)
