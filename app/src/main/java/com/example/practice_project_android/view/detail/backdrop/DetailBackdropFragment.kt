package com.example.practice_project_android.view.detail.backdrop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.FragmentDetailBackdropBinding
import com.example.practice_project_android.view.detail.poster.DetailPosterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBackdropFragment : Fragment() {
    private lateinit var binding: FragmentDetailBackdropBinding
    private val viewModel: DetailPosterViewModel by viewModels()
    private val adapterBackdrop = DetailBackdropAdapter()
    private val args : DetailBackdropFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBackdropBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.title = ""
            setHasOptionsMenu(true)
        }
        viewModel.getImages(movieId)

        binding.rcImages.adapter = adapterBackdrop.apply {
            itemClick = { imgProfile ->
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/$imgProfile").into(binding.imgPoster)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressCircular.isVisible = it
            binding.layoutDetail.isVisible = !it
        }
        viewModel.resultImage.observe(viewLifecycleOwner) {
            adapterBackdrop.data = it.backdrops ?: listOf()
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/${it.backdrops?.get(0)?.file_path}").into(binding.imgPoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
