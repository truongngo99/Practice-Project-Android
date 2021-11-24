package com.example.practice_project_android.view.detail.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.databinding.ActivityDetailBinding
import com.example.practice_project_android.view.home.movie.MovieItemListener

class DetailAdapter(val clickListener : MovieItemListener) : ListAdapter<Movie, DetailViewHolder >(DetailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityDetailBinding.inflate(inflater,parent,false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.binding.layoutDetail.setOnClickListener {
            Log.d("AAA","Da click")
        }
    }
}