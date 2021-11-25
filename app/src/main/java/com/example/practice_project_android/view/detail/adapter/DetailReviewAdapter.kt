package com.example.practice_project_android.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_project_android.data.model.movie.ResultReview
import com.example.practice_project_android.databinding.ItemReviewLayoutBinding


class DetailReviewAdapter : RecyclerView.Adapter<DetailReviewHolder>() {
    var data = listOf<ResultReview>()
    set(value)  {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailReviewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemReviewLayoutBinding.inflate(inflate,parent,false)
        return DetailReviewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailReviewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvContent.text = item.content
    }

    override fun getItemCount(): Int {
        return data.size
    }

}