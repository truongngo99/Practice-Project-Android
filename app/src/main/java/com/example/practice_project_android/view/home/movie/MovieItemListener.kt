package com.example.practice_project_android.view.home.movie

import com.example.practice_project_android.data.model.movie.Movie

class MovieItemListener(val clickListener: (movieId: String)-> Unit){
    fun onClick(movie: Movie)= clickListener(movie.id.toString())
}