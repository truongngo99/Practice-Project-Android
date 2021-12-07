package com.example.practice_project_android.view.home.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Result
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val resultMoviePopular = MutableLiveData<Result>()
    val resultMovieNowPlay = MutableLiveData<Result>()
    val resultMovieTopRate = MutableLiveData<Result>()
    val resultMovieUpCome = MutableLiveData<Result>()
    val resultMovieTrending = MutableLiveData<Result>()
    val isLoading = MutableLiveData<Boolean>()
    val failure = MutableLiveData<String>()
    private val dispatchersIO = Dispatchers.IO

    fun getApiMovie() {
        isLoading.value = true
        viewModelScope.launch(dispatchersIO) {
            val job1 = launch {
                getListMovieNowPlay()
            }
            val job2 = launch {
                getListMoviePopular()
            }
            val job3 = launch {
                getListMovieTopRate()
            }
            val job4 = launch {
                getListMovieUpCome()
            }
            val job5 = launch {
                getListMovieTrending()
            }
            listOf(job1, job2, job3, job4, job5).joinAll()
            withContext(Dispatchers.Main) {
                isLoading.value = false
            }
        }
    }

    private suspend fun getListMovieTrending() {
        val movieTrendiness = repository.getListMovieTrending()
        try {
            withContext(Dispatchers.Main) {
                resultMovieTrending.value = movieTrendiness
            }
        } catch (e: Exception) {
            failure.value = e.toString()
        }
    }

    private suspend fun getListMoviePopular() {
        val moviePopular = repository.getListMoviePopular()
        try {
            withContext(Dispatchers.Main) {
                resultMoviePopular.value = moviePopular
            }
        } catch (e: Exception) {
            failure.value = e.toString()
        }
    }

    private suspend fun getListMovieNowPlay() {
        val movieNowPlay = repository.getListMovieNowPlay()
        try {
            withContext(Dispatchers.Main) {
                resultMovieNowPlay.value = movieNowPlay
            }
        } catch (e: Exception) {
            failure.value = e.toString()
        }
    }

    private suspend fun getListMovieUpCome() {
        val movieUpCome = repository.getListMovieUpCome()
        try {
            withContext(Dispatchers.Main) {
                resultMovieUpCome.value = movieUpCome
            }
        } catch (e: Exception) {
            failure.value = e.toString()
        }
    }

    private suspend fun getListMovieTopRate() {
        val movieTopRate = repository.getListMovieTopRate()
        try {
            withContext(Dispatchers.Main) {
                resultMovieTopRate.value = movieTopRate
            }
        } catch (e: Exception) {
            failure.value = e.toString()
        }
    }
}
