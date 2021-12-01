package com.example.practice_project_android.view.home.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practice_project_android.databinding.FragmentSearchBinding
import com.example.practice_project_android.view.detail.DetailMovieActivity
import com.example.practice_project_android.view.home.movie.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private  val viewModel : SearchViewModel by viewModels()
    private lateinit var binding : FragmentSearchBinding
    private val adapterSearch = SearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewModel.resultSearch.observe(viewLifecycleOwner){
           adapterSearch.data = it.results!!
       }
        binding.rcSearch.adapter = adapterSearch.apply {
            itemClick={
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra("movieId", it)
                startActivity(intent)
            }
        }
        binding.btnSearch.setOnClickListener {
            viewModel.searchMovie(binding.edtSearch.text.toString())
        }

    }
}