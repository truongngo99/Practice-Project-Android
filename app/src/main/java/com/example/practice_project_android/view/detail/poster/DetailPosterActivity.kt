package com.example.practice_project_android.view.detail.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_project_android.databinding.ActivityDetailPosterBinding
import dagger.hilt.android.AndroidEntryPoint

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
        binding.toolBar.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel.getImages(movieId)
        binding.rcImages.adapter = adapterPoster
        viewModel.resultImage.observe(this){
            adapterPoster.data = it.posters ?: listOf()
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