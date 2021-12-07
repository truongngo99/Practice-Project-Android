package com.example.practice_project_android.view.home.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.practice_project_android.databinding.FragmentMovieBinding
import com.example.practice_project_android.view.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.math.abs

@AndroidEntryPoint
class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private val adapterMovieTrending = MovieTrendingAdapter()
    private val adapterMoviePopular = MovieAdapter()
    private val adapterMovieNowPlay = MovieAdapter()
    private val adapterMovieTopRate = MovieAdapter()
    private val adapterMovieUpComing = MovieAdapter()
    private var sliderDelay: Job? = null
    private var sliderScope = CoroutineScope(Dispatchers.Main)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerTrendingMovie.offscreenPageLimit = 3
        makeViewPageSlide()
        observe()
        callApiMovie()
        bindingApdateRc()
    }

    private fun bindingApdateRc() {
        binding.apply {
            rcMovieNowPlaying.adapter = adapterMovieNowPlay.apply {
                itemClick = { movieId ->
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                }
            }
            rcMoviePopular.adapter = adapterMoviePopular.apply {
                itemClick = { movieId ->
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                }
            }
            rcMovieTopRate.adapter = adapterMovieTopRate.apply {
                itemClick = { movieId ->
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                }
            }
            rcMovieUpComing.adapter = adapterMovieUpComing.apply {
                itemClick = { movieId ->
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                }
            }
            pagerTrendingMovie.adapter = adapterMovieTrending
        }
    }

    private fun makeSlider() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.pagerTrendingMovie.setPageTransformer(transformer)
    }

    private fun makeViewPageSlide() {
        makeSlider()
        binding.pagerTrendingMovie.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderDelay?.cancel()
                    sliderDelay = sliderScope.launch {
                        delay(2000L)
                        binding.pagerTrendingMovie.currentItem =
                            (binding.pagerTrendingMovie.currentItem + 1) % adapterMovieTrending.itemCount
                    }
                }
            })
    }

    private fun observe() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
                binding.scrollView.isVisible = !it
            }
            resultMovieTopRate.observe(viewLifecycleOwner) {
                adapterMovieTopRate.data = it.results!!
            }
            resultMovieTrending.observe(viewLifecycleOwner) {
                adapterMovieTrending.data = it.results!!
            }
            resultMovieNowPlay.observe(viewLifecycleOwner) {
                adapterMovieNowPlay.data = it.results!!
            }
            resultMovieUpCome.observe(viewLifecycleOwner) {
                adapterMovieUpComing.data = it.results!!
            }
            resultMoviePopular.observe(viewLifecycleOwner) {
                adapterMoviePopular.data = it.results!!
            }
        }
    }

    private fun callApiMovie() {
        viewModel.getApiMovie()
    }
}
