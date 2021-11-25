package com.example.practice_project_android.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.R
import com.example.practice_project_android.data.model.movie.Crew
import com.example.practice_project_android.databinding.ItemCasterLayoutBinding

class DetailCrewAdapter : RecyclerView.Adapter<DetailCasterHolder>() {
    var data = listOf<Crew>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCasterHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemCasterLayoutBinding.inflate(inflate,parent,false)
        return DetailCasterHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailCasterHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            Glide.with(holder.binding.root.context).load("https://image.tmdb.org/t/p/w500${item.profile_path}")
                .placeholder(R.drawable.default_avatar)
                .into(holder.binding.imgCaster)
            tvNameCaster.text= item.name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}