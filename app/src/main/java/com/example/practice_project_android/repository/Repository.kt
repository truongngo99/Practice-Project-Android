package com.example.practice_project_android.repository

import com.example.practice_project_android.data.api.ApiService
import com.example.practice_project_android.data.model.authenticcation.LoginBody
import com.example.practice_project_android.data.model.authenticcation.RequestToken
import com.example.practice_project_android.data.model.movie.Movie
import javax.inject.Inject

class Repository @Inject constructor( private val apiService: ApiService) {
    suspend fun getRequestToken(): RequestToken {
      return  apiService.getRequestToken()
    }

    suspend fun login( loginBody: LoginBody) : RequestToken {
        return apiService.login(loginBody)
    }

    suspend fun getListMovieTrending() : Movie {
        return  apiService.getListMovieTrending()
    }

    suspend fun getListMoviePopular() : Movie {
        return apiService.getListMoviePopular()
    }

    suspend fun getListMovieTopRate() : Movie {
        return apiService.getListMovieTopRate()
    }

    suspend fun getListMovieNowPlay() : Movie {
        return apiService.getListMoviePlaying()
    }

    suspend fun getListMovieUpCome() : Movie {
        return apiService.getListMovieUpcoming()
    }
}