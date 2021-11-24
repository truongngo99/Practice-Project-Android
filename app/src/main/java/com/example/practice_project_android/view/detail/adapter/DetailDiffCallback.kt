package com.example.practice_project_android.view.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.practice_project_android.data.model.movie.Movie

class DetailDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}