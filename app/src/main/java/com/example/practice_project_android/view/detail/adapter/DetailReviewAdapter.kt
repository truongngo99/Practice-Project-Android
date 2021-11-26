package com.example.practice_project_android.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project_android.R
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
        holder.binding.apply {
            tvContent.text = item.content
            txtName.text = item.author_details?.name
            tvDate.text = item.created_at
            if (item.author_details?.rating == null){
                tvRating.text = 0.toString()
            } else{
                tvRating.text = item.author_details.rating.toString()
            }

            Glide.with(root).load(item.author_details?.avatar_path)
                .placeholder(R.drawable.default_avatar)
                .into(imgAvatar)

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}