package com.example.practice_project_android.view.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityDetailBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private val viewModel : DetailMovieViewModel by viewModels()
      var movieId : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movieId",0)
        viewModel.getDetailMovie(movieId)
        viewModel.resultDetailMovie.observe(this){
            binding.header.apply {
                Glide.with(root).load("https://image.tmdb.org/t/p/original${it.backdrop_path}").into(imgBackdrop)
                Glide.with(root).load("https://image.tmdb.org/t/p/w500${it.poster_path}").into(imgPoster)
                tvTitleMovie.text = it.title
                ratingBar.rating = ((it.vote_average ?: 0.0)/2).toFloat()
                tvVoteAverage.text = it.vote_average.toString()
                tvVoteCount.text = it.vote_count.toString()

            }
            binding.layoutBody.tvOverview.text = it.overview
            binding.collapsingToolbarLayout.title = it.title

        }
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                return true
            }
        }
        return  super.onOptionsItemSelected(item)
    }
}