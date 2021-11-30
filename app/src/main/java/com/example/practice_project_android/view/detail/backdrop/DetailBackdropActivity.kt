package com.example.practice_project_android.view.detail.backdrop

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityDetailBackdropBinding
import com.example.practice_project_android.view.detail.poster.DetailPosterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBackdropActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBackdropBinding
    private val viewModel : DetailPosterViewModel by viewModels()
    private val adapterBackdrop = DetailBackdropAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBackdropBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("movieId",0)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel.getImages(movieId)

        binding.rcImages.adapter = adapterBackdrop.apply {
            itemClick={imgProflie->
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/$imgProflie").into(binding.imgPoster)

            }
        }
        viewModel.resultImage.observe(this){
            adapterBackdrop.data = it.backdrops ?: listOf()
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/${it.backdrops?.get(0)?.file_path}").into(binding.imgPoster)
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
