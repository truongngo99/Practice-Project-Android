package com.example.practice_project_android.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import com.example.practice_project_android.R
import com.example.practice_project_android.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val currentIndex = 0
    private lateinit var binding: ActivityHomeBinding
    @Inject lateinit var homeViewAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()

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

        if (binding.pager.adapter == null) {
            binding.pager.adapter = homeViewAdapter
            setAdapter()
            if (currentIndex != 0) {
                binding.pager.doOnPreDraw {
                    binding.pager.currentItem = currentIndex
                }
            }
        }
        binding.pager.isUserInputEnabled = false
    }

    private fun setAdapter() {
        if (binding.pager.adapter == null) {
            binding.pager.adapter = homeViewAdapter
            if (currentIndex != 0) {
                binding.pager.doOnPreDraw {
                    binding.pager.currentItem = currentIndex
                }
            }
        }
    }
}
