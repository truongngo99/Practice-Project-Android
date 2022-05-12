package com.example.practice_project_android.view.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val movieId = savedStateHandle.get<Int>("movieId") ?: throw Exception("Missing Movie Id")
    private val _viewState = MutableStateFlow(DetailMovieState.Empty)
    val viewState : StateFlow<DetailMovieState> = _viewState.asStateFlow()
    var isLoading : Boolean = false

    //    val failure = MutableLiveData<String>()
    init {
        isLoading = true
        getDetailMovie()
    }

    fun getDetailMovie() {
        isLoading = true
        viewModelScope.launch {
            val detailMovie = repository.getDetailMovie(movieId)
            Log.i("MovieDetail",detailMovie.toString())
            _viewState.update {
                it.copy(
                    isLoading = !isLoading,
                    resultDetailMovie = detailMovie,
                )
            }
        }
    }
}
