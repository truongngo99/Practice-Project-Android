package com.example.practice_project_android.view.home.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Movie
import com.example.practice_project_android.databinding.ItemSearchLayoutBinding

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    var data = listOf<Movie>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var itemClick : ((Int)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemSearchLayoutBinding.inflate(inflate,parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            Glide.with(root.context).load("https://image.tmdb.org/t/p/original${item.poster_path}").into(imgPoster)
            tvTitleMovie.text =item.title
            tvDate.text =item.release_date
            itemSearch.setOnClickListener {
                itemClick?.invoke(item.id ?: 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}