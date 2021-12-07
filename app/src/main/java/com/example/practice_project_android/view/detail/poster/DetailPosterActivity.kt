package com.example.practice_project_android.view.detail.poster

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityDetailPosterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPosterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPosterBinding
    private val viewModel: DetailPosterViewModel by viewModels()
    private val adapterPoster = DetailPosterAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPosterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("movieId", 0)
        viewModel.getImages(movieId)
        supportToolBar()
        binding.rcImages.adapter = adapterPoster.apply {
            itemClick = { imgProfile ->
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/$imgProfile").into(binding.imgPoster)
            }
        }
        viewModel.isLoading.observe(this) {
            binding.progressCircular.isVisible = it
            binding.layoutDetail.isVisible = !it
        }
        viewModel.resultImage.observe(this) {
            adapterPoster.data = it.posters ?: listOf()
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/${it.posters?.get(0)?.file_path}").into(binding.imgPoster)
        }
    }

    private fun supportToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
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
