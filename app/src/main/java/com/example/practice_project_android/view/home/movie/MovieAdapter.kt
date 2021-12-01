package com.example.practice_project_android.view.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.databinding.ItemMoviePopularBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var itemClick : ((Int) -> Unit)? =null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviePopularBinding.inflate(inflater, parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            tvTitleMovie.text = item.title
            Glide.with(root.context).load("https://image.tmdb.org/t/p/original${item.poster_path}")
                .into(imgPoster)
            itemMovie.setOnClickListener{
                item.id?.let { itemClick?.invoke(it) }
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

