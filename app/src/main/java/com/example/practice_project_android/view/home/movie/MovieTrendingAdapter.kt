package com.example.practice_project_android.view.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.databinding.ItemMovieTrendingBinding

class MovieTrendingAdapter : RecyclerView.Adapter<MovieTrendingViewHolder>() {
    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrendingViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemMovieTrendingBinding.inflate(inflate, parent, false)
        return MovieTrendingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTrendingViewHolder, position: Int) {
        val item = data[position]

        Glide.with(holder.binding.root.context).load("https://image.tmdb.org/t/p/original${item.backdrop_path}")
            .into(holder.binding.imgPoster)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
