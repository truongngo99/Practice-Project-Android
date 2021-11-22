package com.example.practice_project_android.view.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.example.practice_project_android.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding
    private val viewModel : MovieViewModel by viewModels()
    val adapter = MovieAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel.getListMovieTrending()
//        viewModel.getListMovieNowPlay()
        viewModel.apply {
            getListMovieNowPlay()
            getListMoviePopular()
            getListMovieTopRate()
            getListMovieTrending()
            getListMovieUpCome()
        }
//        binding.rcMovieTrending.adapter = adapter
//        binding.rcMovieNowPlaying.adapter = adapter
        binding.apply {
            rcMovieNowPlaying.adapter = adapter
            rcMoviePopular.adapter = adapter
            rcMovieTrending.adapter = adapter
            rcMovieTopRate.adapter = adapter
            rcMovieUpComing.adapter = adapter
        }

        viewModel.apply {
            resultMovieTopRate.observe(this@MovieActivity){
                adapter.data = it.results
            }
            resultMovieNowPlay.observe(this@MovieActivity){
                adapter.data = it.results
            }
            resultMovieUpCome.observe(this@MovieActivity){
                adapter.data = it.results
            }
            resultMoviePopular.observe(this@MovieActivity){
                adapter.data = it.results
            }
            resultMovieTrending.observe(this@MovieActivity) {
                adapter.data = it.results
            }
        }
    }
}