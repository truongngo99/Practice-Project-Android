package com.example.practice_project_android.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.ResultVideo
import com.example.practice_project_android.databinding.ItemTrailerLayoutBinding

class DetailTrailerAdapter : RecyclerView.Adapter<DetailTrailerHolder>() {
    var data = listOf<ResultVideo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var itemClick : ((String) -> Unit)? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTrailerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemTrailerLayoutBinding.inflate(inflate,parent,false)
        return DetailTrailerHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailTrailerHolder, position: Int) {
        val item = data[position]
        holder.binding.imgTrailer.setOnClickListener {
            itemClick?.invoke(item.key ?: "")
        }
        holder.binding.apply {
            Glide.with(holder.binding.root).load("https://img.youtube.com/vi/${item.key}/0.jpg").into(imgTrailer)
            tvNameTrailer.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}