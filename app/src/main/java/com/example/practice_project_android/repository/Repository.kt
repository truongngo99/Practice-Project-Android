package com.example.practice_project_android.repository

import com.example.practice_project_android.data.api.ApiService
import com.example.practice_project_android.data.model.authenticcation.LoginBody
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.data.model.movie.Images
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.data.model.movie.PeopleCast
import com.example.practice_project_android.data.model.movie.Result
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRequestToken(): RequestToken {
        return apiService.getRequestToken()
    }

    suspend fun login(loginBody: LoginBody): RequestToken {
        return apiService.login(loginBody)
    }

    suspend fun getListMovieTrending(): Result {
        return apiService.getListMovieTrending()
    }

    suspend fun getListMoviePopular(): Result {
        return apiService.getListMoviePopular()
    }

    suspend fun getListMovieTopRate(): Result {
        return apiService.getListMovieTopRate()
    }

    suspend fun getListMovieNowPlay(): Result {
        return apiService.getListMoviePlaying()
    }

    suspend fun getListMovieUpCome(): Result {
        return apiService.getListMovieUpcoming()
    }

    suspend fun getDetailMovie(movieId: Int): Movie {
        return apiService.getDetailMovie(
            movieId = movieId,
            appendToResponse = "images,videos,casts,reviews"
        )
    }

    suspend fun getImages(movieId: Int): Images {
        return apiService.getImages(movieId = movieId)
    }
    suspend fun getInfoCaster(castId: Int): PeopleCast {
        return apiService.getInfoCaster(castId = castId)
    }

    suspend fun searchMovie(keySearch: String): Result {
        return apiService.searchMovie(key = keySearch)
    }
}
