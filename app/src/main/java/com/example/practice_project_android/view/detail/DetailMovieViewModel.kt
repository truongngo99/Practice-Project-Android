package com.example.practice_project_android.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val resultDetailMovie = MutableLiveData<Movie>()
    val isLoading = MutableLiveData<Boolean>()
    val failure = MutableLiveData<String>()
    fun getDetailMovie(movieId: Int) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val detailMovie = repository.getDetailMovie(movieId)
            try {
                withContext(Dispatchers.Main) {
                    resultDetailMovie.value = detailMovie
                    isLoading.value = false
                }
            } catch (e: Exception) {
                failure.value = e.toString()
            }
        }
    }
}
