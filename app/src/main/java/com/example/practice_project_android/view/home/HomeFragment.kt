package com.example.practice_project_android.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.example.practice_project_android.R
import com.example.practice_project_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var currentIndex = 0
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        Log.e("ERROR", "Call")
        binding.pager.isSaveEnabled = false

        binding.bottomNav.setOnNavigationItemSelectedListener {
            val page = when (it.itemId) {
                R.id.home -> 0
                R.id.search -> 1
                R.id.account -> 2
                R.id.settings -> 3
                else -> -1
            }
            binding.pager.currentItem = page
            true
        }

    }

    private fun setUpViewPager() {
        binding.pager.offscreenPageLimit = 4
        binding.pager.adapter = HomeAdapter(requireActivity())
        if (currentIndex != 0) {
            binding.pager.doOnPreDraw {
                binding.pager.currentItem = currentIndex
            }
        }
        binding.pager.isUserInputEnabled = false
    }
}
