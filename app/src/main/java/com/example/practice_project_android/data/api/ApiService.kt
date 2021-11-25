package com.example.practice_project_android.data.api

import com.example.practice_project_android.data.model.authenticcation.LoginBody
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.data.model.movie.Result
import retrofit2.http.*

interface ApiService {
    @GET("authentication/token/new")
    suspend fun getRequestToken(): RequestToken

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body loginBody: LoginBody): RequestToken

    @GET("trending/all/day")
    suspend fun getListMovieTrending() : Result

    @GET("movie/popular")
    suspend fun getListMoviePopular() : Result


    @GET("movie/now_playing")
    suspend fun getListMoviePlaying() : Result

    @GET("movie/top_rated")
    suspend fun getListMovieTopRate() : Result

    @GET("movie/upcoming")
    suspend fun getListMovieUpcoming() : Result

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId : Int,
        @Query("append_to_response") appendToResponse : String? = null
    ) : Movie
}