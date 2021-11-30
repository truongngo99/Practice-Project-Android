package com.example.practice_project_android.view.detail.backdrop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Backdrop
import com.example.practice_project_android.databinding.ItemBackdropLayoutBinding

class DetailBackdropAdapter : RecyclerView.Adapter<DetailBackdropViewHolder>() {
    var data = listOf<Backdrop>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var itemClick  : ((String)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBackdropViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemBackdropLayoutBinding.inflate(inflate,parent,false)
        return DetailBackdropViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailBackdropViewHolder, position: Int) {
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