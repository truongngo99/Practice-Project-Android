package com.example.practice_project_android.data.model.movie

data class Result(
    val page: Int?,
    val results: List<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
)
