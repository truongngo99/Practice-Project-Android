package com.example.practice_project_android.view.detail.caster

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practice_project_android.databinding.ActivityCasterLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCasterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCasterLayoutBinding
    private val viewModel : DetailCasterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCasterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val casterId = intent.getIntExtra("casterId",0)
        viewModel.getInfoCaster(casterId)
        viewModel.resultInfoCaster.observe(this){
            binding.apply {
                Glide.with(root).load("https://image.tmdb.org/t/p/original${it.profile_path}").into(imgAvatar)
                tvName.text = it.name
//                binding.layoutBody.apply {
//                    txtKnownFor.text = it.known_for_department
//                    txtBirthday.text = it.birthday
//                    txtGender.text = it.gender.toString()
//                    txtPlaceBirth.text = it.place_of_birth
//                    txtBiography.text=it.biography
//                }

            }
        }
    }
}