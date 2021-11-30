package com.example.practice_project_android.view.detail.poster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityDetailPosterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class DetailPosterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPosterBinding
    private val viewModel : DetailPosterViewModel by viewModels()
    private val adapterPoster = DetailPosterAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPosterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("movieId",0)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel.getImages(movieId)

        binding.rcImages.adapter = adapterPoster.apply {
            itemClick={imgProflie->
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/$imgProflie").into(binding.imgPoster)

            }
        }
        viewModel.resultImage.observe(this){
            adapterPoster.data = it.posters ?: listOf()
           //val value = it.posters?.get(0)
            //Log.d("CCCC",value.toString())

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/${it.posters?.get(0)?.file_path}").into(binding.imgPoster)
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