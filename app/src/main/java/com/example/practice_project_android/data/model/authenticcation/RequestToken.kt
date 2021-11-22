package com.example.practice_project_android.data.model.authenticcation

import com.squareup.moshi.Json

data class RequestToken(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "expires_at")
    val expires_at:String,
    @Json(name = "request_token")
    val request_token:String,
)
