package com.example.practice_project_android.view.home.search

import android.app.Activity
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
import android.text.Editable

import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import com.example.practice_project_android.BaseActivity
import java.util.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.hardware.input.InputManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.internal.ThreadUtil


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
        binding.progressCircular.visibility = View.INVISIBLE
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressCircular.isVisible = it
            binding.rcSearch.isVisible = !it
        }
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
//        binding.edtSearch.setOnEditorActionListener { _, i, keyEvent ->
//            if (i == EditorInfo.IME_ACTION_DONE){
//                viewModel.searchMovie(binding.edtSearch.text.toString())
//                true
//            }
//             false
//        }
        binding.edtSearch.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                private var handler: Handler = Handler(Looper.getMainLooper())
                private val DELAY: Long = 1000 // Milliseconds
                override fun afterTextChanged(s: Editable) {
                    Log.d("postDelay", s.toString())
                    handler.postDelayed({
                        if (binding.edtSearch.text.isNullOrEmpty()){
                            hideKeyboard()

                        } else {
                            hideKeyboard()
                            viewModel.searchMovie(binding.edtSearch.text.toString())
                        }
                    },3000)

                }
            }
        )


    }

    fun hideKeyboard(){
       activity?.run {
           val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
           imm?.hideSoftInputFromWindow(view?.windowToken,0)
           currentFocus?.clearFocus()
       }
    }

}