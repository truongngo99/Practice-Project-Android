package com.example.practice_project_android.view.detail.caster

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityCasterLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCasterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCasterLayoutBinding
    private val viewModel: DetailCasterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCasterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val casterId = intent.getIntExtra("casterId", 0)
        viewModel.getInfoCaster(casterId)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        observale()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun observale() {
        viewModel.isLoading.observe(this) {
            binding.progressCircular.isVisible = it
            binding.layoutDetailCaster.isVisible = !it
        }
        viewModel.resultInfoCaster.observe(this) {
            binding.apply {
                Glide.with(root).load("https://image.tmdb.org/t/p/original${it.profile_path}").into(imgAvatar)
                tvName.text = it.name
                tvAbout.text = "\t${it.biography}"
                tvDate.text = it.birthday
                tvPopularity.text = it.popularity.toString()
                tvPlaceBirthday.text = it.place_of_birth
                if (it.gender == 1) {
                    tvGender.text = "Female"
                } else {
                    tvGender.text = "Male"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
