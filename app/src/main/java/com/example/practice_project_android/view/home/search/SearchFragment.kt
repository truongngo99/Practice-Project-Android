package com.example.practice_project_android.view.home.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practice_project_android.databinding.FragmentSearchBinding
import com.example.practice_project_android.view.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import android.os.Handler
import android.os.Looper
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
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
        binding.progressCircular.visibility = View.INVISIBLE
        observable()
        binding.rcSearch.adapter = adapterSearch.apply {
            itemClick = {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra("movieId", it)
                startActivity(intent)
            }
        }
        binding.edtSearch.setOnEditorActionListener { _, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                viewModel.searchMovie(binding.edtSearch.text.toString())
                
            }
            false
        }
        binding.edtSearch.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    if (binding.edtSearch.text.isNullOrEmpty()) {
                        hideKeyboard()
                    } else {
                        hideKeyboard()
                        viewModel.searchMovie(binding.edtSearch.text.toString())
                    }

                }, 3000)
            }
        }


    }
    private fun observable(){
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressCircular.isVisible = it
            binding.rcSearch.isVisible = !it
        }
        viewModel.resultSearch.observe(viewLifecycleOwner) {
            adapterSearch.data = it.results!!
        }
    }
    private fun hideKeyboard() {
        activity?.run {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view?.windowToken, 0)
            currentFocus?.clearFocus()
        }
    }

}