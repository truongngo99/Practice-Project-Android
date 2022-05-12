package com.example.practice_project_android.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practice_project_android.data.model.movie.Cast
import com.example.practice_project_android.databinding.FragmentDetailBinding
import com.example.practice_project_android.view.detail.adapter.DetailCasterAdapter
import com.example.practice_project_android.view.detail.adapter.DetailCrewAdapter
import com.example.practice_project_android.view.detail.adapter.DetailReviewAdapter
import com.example.practice_project_android.view.detail.adapter.DetailTrailerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailMovieViewModel by viewModels()

    private val adapterCaster = DetailCasterAdapter()
    private val adapterCew = DetailCrewAdapter()
    private val adapterTrailer = DetailTrailerAdapter()
    private val adapterReview = DetailReviewAdapter()
    private val args : DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingHeader()
        bindingBody()
        observable()
        supportToolBar()

    }


    private fun supportToolBar() {
       binding.btnBack.setOnClickListener {
           findNavController().navigateUp()
       }



    }


    private fun bindingBody() {
        binding.layoutBody.rcCrew.adapter = adapterCew
        binding.layoutBody.rvReview.adapter = adapterReview
        binding.layoutBody.rcTrailer.adapter = adapterTrailer.apply {
            itemClick = { key ->
                val intent = Intent(Intent.ACTION_VIEW)

                intent.data = Uri.parse("vnd.youtube:$key")
                intent.setPackage("com.google.android.youtube")
                startActivity(intent)
            }
        }
        binding.layoutBody.rcCaster.adapter = adapterCaster.apply {
            itemClick = { casterId ->
                val action = DetailMovieFragmentDirections.actionDetailMovieFragmentToDetailCasterFragment(casterId)
                findNavController().navigate(action)
            }

        }
    }

    private fun bindingHeader() {
        binding.header.imgPoster.setOnClickListener {
                    val action = DetailMovieFragmentDirections.actionDetailMovieFragmentToDetailPosterFragment(movieId = args.movieId)
            findNavController().navigate(action)

        }
        binding.header.imgBackdrop.setOnClickListener {
            val action = DetailMovieFragmentDirections.actionDetailMovieFragmentToDetailBackdropFragment(movieId = args.movieId)
            findNavController().navigate(action)
        }
    }

    private fun observable() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collectLatest {
                binding.header.apply {
                    Glide.with(root).load("https://image.tmdb.org/t/p/original${it.resultDetailMovie?.backdrop_path}")
                        .into(imgBackdrop)
                    Glide.with(root).load("https://image.tmdb.org/t/p/w500${it.resultDetailMovie?.poster_path}")
                        .into(imgPoster)
                    tvTitleMovie.text = it.resultDetailMovie?.title
                    ratingBar.rating = ((it.resultDetailMovie?.vote_average ?: 0.0) / 2).toFloat()
                    tvVoteAverage.text = it.resultDetailMovie?.vote_average.toString()
                    tvVoteCount.text = it.resultDetailMovie?.vote_count.toString()
                }
                adapterCaster.data = it.resultDetailMovie?.casts?.cast ?: listOf()

                adapterTrailer.data = it.resultDetailMovie?.videos?.results ?: listOf()
                adapterReview.data = it.resultDetailMovie?.reviews?.results ?: listOf()
                adapterCew.data = it.resultDetailMovie?.casts?.crew ?: listOf()
                binding.layoutBody.tvOverview.text = "\t${it.resultDetailMovie?.overview}"
                binding.collapsingToolbarLayout.title = it.resultDetailMovie?.title
                binding.progressCircular.isVisible = it.isLoading
            }
        }


    }


}
