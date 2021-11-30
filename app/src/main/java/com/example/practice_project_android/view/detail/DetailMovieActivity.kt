package com.example.practice_project_android.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityDetailBinding
import com.example.practice_project_android.view.detail.adapter.DetailCasterAdapter
import com.example.practice_project_android.view.detail.adapter.DetailCrewAdapter
import com.example.practice_project_android.view.detail.adapter.DetailReviewAdapter
import com.example.practice_project_android.view.detail.adapter.DetailTrailerAdapter
import com.example.practice_project_android.view.detail.backdrop.DetailBackdropActivity
import com.example.practice_project_android.view.detail.caster.DetailCasterActivity
import com.example.practice_project_android.view.detail.poster.DetailPosterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailMovieViewModel by viewModels()
    var movieId: Int = 0
    private val adapterCaster = DetailCasterAdapter()
    private val adapterCew = DetailCrewAdapter()
    private val adapterTrailer = DetailTrailerAdapter()
    private val adapterReview = DetailReviewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("movieId", 0)
        viewModel.getDetailMovie(movieId)
        binding.header.imgPoster.setOnClickListener {
            val intent = Intent(this, DetailPosterActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        }
        binding.header.imgBackdrop.setOnClickListener {
            val intent = Intent(this, DetailBackdropActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        }
        binding.layoutBody.rcCrew.adapter = adapterCew
        binding.layoutBody.rvReview.adapter = adapterReview
        binding.layoutBody.rcTrailer.adapter = adapterTrailer.apply {
            itemClick = { key ->
                val intent = Intent(Intent.ACTION_VIEW)

                intent.data = Uri.parse("vnd.youtube:$key")
                intent.setPackage("com.google.android.youtube");
                startActivity(intent)
            }
        }
        binding.layoutBody.rcCaster.adapter = adapterCaster.apply {
            itemClick = { casterId ->
                val intent = Intent(this@DetailMovieActivity, DetailCasterActivity::class.java)
                intent.putExtra("casterId", casterId)
                startActivity(intent)
            }
        }
        observable()
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun observable() {
        viewModel.resultDetailMovie.observe(this) {
            binding.header.apply {
                Glide.with(root).load("https://image.tmdb.org/t/p/original${it.backdrop_path}")
                    .into(imgBackdrop)
                Glide.with(root).load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                    .into(imgPoster)
                tvTitleMovie.text = it.title
                ratingBar.rating = ((it.vote_average ?: 0.0) / 2).toFloat()
                tvVoteAverage.text = it.vote_average.toString()
                tvVoteCount.text = it.vote_count.toString()

            }

            adapterCaster.data = it.casts?.cast ?: listOf()
            adapterTrailer.data = it.videos?.results ?: listOf()
            adapterReview.data = it.reviews?.results ?: listOf()
            adapterCew.data = it.casts?.crew ?: listOf()
            binding.layoutBody.tvOverview.text = "\t${it.overview}"
            binding.collapsingToolbarLayout.title = it.title

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}