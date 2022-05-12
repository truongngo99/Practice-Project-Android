package com.example.practice_project_android.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practice_project_android.R
import com.example.practice_project_android.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        observable()
    }


    private fun observable() {
        viewModel.result.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFragment)
        }
        viewModel.failure.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "Request Token Failure", Toast.LENGTH_LONG).show()

        }
    }

    private fun loadData() {
        viewModel.getToken()
    }
}
