package com.example.practice_project_android.data.model.movie

data class PeopleCast(
    val adult: Boolean?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val order: Int?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val also_known_as: List<String>?,
    val biography: String?,
    val birthday: String?,
    val deathday: String?,
    val homepage: String?,
    val imdb_id: String?,
    val place_of_birth: String?,
)
