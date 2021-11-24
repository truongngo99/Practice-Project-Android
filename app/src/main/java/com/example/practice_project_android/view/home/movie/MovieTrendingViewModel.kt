package com.example.practice_project_android.view.home.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Result
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieTrendingViewModel @Inject constructor(val repository: Repository): ViewModel() {
    val resultMovieTrending = MutableLiveData<Result>()
    val failure = MutableLiveData<String>()
    fun getListMovieTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieTrendiness = repository.getListMovieTrending()
            try {
                withContext(Dispatchers.Main) {
                    resultMovieTrending.value = movieTrendiness
                }
            } catch (e: Exception) {
                failure.value = e.toString()
            }

        }
    }
}