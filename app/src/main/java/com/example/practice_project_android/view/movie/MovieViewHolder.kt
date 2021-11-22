package com.example.practice_project_android.view.movie


import androidx.recyclerview.widget.RecyclerView
import com.example.practice_project_android.databinding.ItemMovieNowPlayingBinding
import com.example.practice_project_android.databinding.ItemMoviePopularBinding

class MovieViewHolder(
    val binding: ItemMoviePopularBinding,
    val binding1:ItemMovieNowPlayingBinding )
    : RecyclerView.ViewHolder(binding1.root) {
}