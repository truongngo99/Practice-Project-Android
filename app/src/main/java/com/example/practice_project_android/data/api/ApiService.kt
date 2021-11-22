package com.example.practice_project_android.data.api

import com.example.practice_project_android.data.model.authenticcation.LoginBody
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.data.model.movie.Movie
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("authentication/token/new?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getRequestToken(): RequestToken

    @POST("authentication/token/validate_with_login?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun login(@Body loginBody: LoginBody): RequestToken

    @GET("trending/all/day?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getListMovieTrending() : Movie

    @GET("movie/popular?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getListMoviePopular() : Movie


    @GET("movie/now_playing?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getListMoviePlaying() : Movie

    @GET("movie/top_rated?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getListMovieTopRate() : Movie

    @GET("movie/upcoming?api_key=a7e38c80a0efc42034dfb5c8b95a72cb")
    suspend fun getListMovieUpcoming() : Movie
}