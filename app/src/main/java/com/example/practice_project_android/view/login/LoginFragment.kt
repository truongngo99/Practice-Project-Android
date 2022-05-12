package com.example.practice_project_android.view.login

import android.content.Context.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practice_project_android.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observable()
        binding.btnLogin.setOnClickListener {

            hideKeyboard()
            login(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
        }
    }


    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
//        val imm = getSystemService(activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
//        currentFocus?.clearFocus()
    }

    private fun observable() {
        viewModel.result.observe(viewLifecycleOwner) {
            findNavController().navigate(com.example.practice_project_android.R.id.homeFragment)
        }
        viewModel.loginFailure.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnLogin.visibility = View.VISIBLE
            Toast.makeText(activity, "Login failure!", Toast.LENGTH_LONG).show()
        }
    }

    private fun login(username: String, password: String) {
        if (validate(username, password)) {
            viewModel.login(username, password)
        }
    }

    private fun validate(username: String, password: String): Boolean {
        var isValidate = true
        binding.btnLogin.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        if (username.isEmpty()) {
            binding.edtUsername.error = "Please enter your name"
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            isValidate = false
        }
        if (password.isEmpty()) {
            binding.edtPassword.error = "Please enter your password"
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            isValidate = false
        }
        return isValidate
    }
}
