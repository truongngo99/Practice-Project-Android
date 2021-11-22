package com.example.practice_project_android.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.R
import com.example.practice_project_android.data.model.movie.Result
import com.example.practice_project_android.databinding.ItemMovieNowPlayingBinding
import com.example.practice_project_android.databinding.ItemMoviePopularBinding

import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    var data = listOf<Result>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviePopularBinding.inflate(inflater,parent,false)
        val binding1 = ItemMovieNowPlayingBinding.inflate(inflater,parent,false)
        return MovieViewHolder(binding,binding1)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            tvTitleMovie.text = item.title
            Glide.with(root.context).load("https://image.tmdb.org/t/p/original${item.poster_path}").into(imgPoster)
        }
        holder.binding1.apply {
            tvTitleMovie.text = item.title
            Glide.with(root.context).load("https://image.tmdb.org/t/p/original${item.poster_path}").into(imgPoster)
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }
}