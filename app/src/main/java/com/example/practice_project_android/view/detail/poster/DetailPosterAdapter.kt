package com.example.practice_project_android.view.detail.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Images
import com.example.practice_project_android.data.model.movie.Poster
import com.example.practice_project_android.databinding.ActivityDetailPosterBinding
import com.example.practice_project_android.databinding.ItemPosterLayoutBinding

class DetailPosterAdapter : RecyclerView.Adapter<DetailPosterViewHolder>() {
    var data = listOf<Poster>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var itemClick : ((String) -> Unit)? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPosterViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemPosterLayoutBinding.inflate(inflate,parent,false)
        return DetailPosterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailPosterViewHolder, position: Int) {
        val item = data[position]
        holder.binding.root.setOnClickListener {
            itemClick?.invoke(item.file_path ?: "")
        }

        Glide.with(holder.binding.root).load("https://image.tmdb.org/t/p/w500/${item.file_path}").into(holder.binding.imgPoster)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}