package com.example.practice_project_android.view.detail.poster

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.FragmentDetailPosterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPosterFragment : Fragment() {
    private lateinit var binding: FragmentDetailPosterBinding
    private val viewModel: DetailPosterViewModel by viewModels()
    private val adapterPoster = DetailPosterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPosterBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val movieId = intent.getIntExtra("movieId", 0)
//        viewModel.getImages(movieId)
        supportToolBar()
        binding.rcImages.adapter = adapterPoster.apply {
            itemClick = { imgProfile ->
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/$imgProfile").into(binding.imgPoster)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressCircular.isVisible = it
            binding.layoutDetail.isVisible = !it
        }
        viewModel.resultImage.observe(viewLifecycleOwner) {
            adapterPoster.data = it.posters ?: listOf()
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500/${it.posters?.get(0)?.file_path}").into(binding.imgPoster)
        }
    }

    private fun supportToolBar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolBar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title =""
            setHasOptionsMenu(true)

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
