package com.example.practice_project_android.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKey @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url
        val newUrl = originalUrl.newBuilder().apply {
            addQueryParameter("api_key", "a7e38c80a0efc42034dfb5c8b95a72cb")
        }.build()
        val builder = original.newBuilder().url(newUrl)
        builder.addHeader("Content-type", "application/json;charset=utf-8")
        val request = builder.build()
        return chain.proceed(request)
    }
}
