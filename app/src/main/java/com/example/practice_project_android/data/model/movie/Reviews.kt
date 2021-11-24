package com.example.practice_project_android.data.model.movie

data class Reviews(
    val page: Int?,
    val results: List<ResultReview>?,
    val total_pages: Int?,
    val total_results: Int?
)