package com.example.practice_project_android.view.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val resultMovieTrending = MutableLiveData<Movie>()
    val resultMoviePopular = MutableLiveData<Movie>()
    val resultMovieNowPlay = MutableLiveData<Movie>()
    val resultMovieTopRate = MutableLiveData<Movie>()
    val resultMovieUpCome = MutableLiveData<Movie>()
    val failure = MutableLiveData<String>()
    fun getListMovieTrending(){
        viewModelScope.launch(Dispatchers.IO) {
            val movieTrendiness = repository.getListMovieTrending()
            try {
                withContext(Dispatchers.Main){
                    resultMovieTrending.value = movieTrendiness
                }
            } catch (e : Exception){
                failure.value = e.toString()
            }

        }
    }

    fun getListMoviePopular() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviePopular = repository.getListMoviePopular()
            try {
                withContext(Dispatchers.Main) {
                    resultMoviePopular.value = moviePopular
                }
            } catch (e : Exception) {
                failure.value = e.toString()
            }

        }
    }

    fun getListMovieNowPlay() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieNowPlay = repository.getListMovieNowPlay()
            try {
                withContext(Dispatchers.Main) {
                    resultMovieNowPlay.value = movieNowPlay
                }
            } catch (e : Exception) {
                failure.value = e.toString()
            }

        }
    }

    fun getListMovieUpCome() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieUpCome = repository.getListMovieUpCome()
            try {
                withContext(Dispatchers.Main) {
                    resultMovieUpCome.value = movieUpCome
                }
            } catch (e : Exception) {
                failure.value = e.toString()
            }

        }
    }
    fun getListMovieTopRate() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieTopRate = repository.getListMovieTopRate()
            try {
                withContext(Dispatchers.Main) {
                    resultMovieTopRate.value = movieTopRate
                }
            } catch (e : Exception) {
                failure.value = e.toString()
            }

        }
    }
}