package com.example.practice_project_android.view.detail

import com.example.practice_project_android.data.model.movie.Movie

data class DetailMovieState(
    val resultDetailMovie : Movie?,
    val isLoading: Boolean,
) {
    companion object{
        val Empty = DetailMovieState(
            isLoading = true,
            resultDetailMovie = null
        )
    }
}