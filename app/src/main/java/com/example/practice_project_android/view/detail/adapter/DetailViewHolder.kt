package com.example.practice_project_android.view.detail.adapter

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_project_android.R
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.databinding.ActivityDetailBinding
import com.example.practice_project_android.view.home.movie.ItemMovieListener

 class DetailViewHolder(val binding: ActivityDetailBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(movie:Movie, listener : ItemMovieListener, ){
        binding.apply {
            layoutDetail.setOnClickListener {
                movie.id.let { listener.onClick(movie)}
            }
        }
    }
}